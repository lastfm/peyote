/*
 * Copyright 2013 Last.fm
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package fm.last.peyote.cacti;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PeyoteCactiLauncher {
  private static final Logger log = LoggerFactory.getLogger(PeyoteCactiLauncher.class);

  public static void main(String[] args) throws JAXBException, IOException {
    if (args.length < 2 || args.length > 3) {
      printUsage();
    }
    String name = args[0];
    String url = args[1];
    ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/peyote.xml");
    applicationContext.registerShutdownHook();
    try {
      InputData inputData = createInputData(args, applicationContext, name, url);
      PeyoteMarshaller marshaller = applicationContext.getBean(PeyoteMarshaller.class);
      marshaller.setInputData(inputData);
      log.info("Starting Peyote");
      File file = new File("datatemplate.xml");
      Writer outWriter = new FileWriter(file);
      marshaller.generateCactiDataTemplate(outWriter);
      outWriter.close();
      log.info("generated data template for '" + name + "' in " + file.getAbsolutePath());

      // file = new File("graphtemplate.xml");
      // outWriter = new FileWriter(file);
      // marshaller.generateCactiGraphTemplate(outWriter);
      // outWriter.close();
      // log.info("generated data template for '" + name + "' in " +
      // file.getAbsolutePath());

      log.info("Peyote finished.");
    } finally {
      applicationContext.close();
    }
  }

  private static InputData createInputData(String[] args, ConfigurableApplicationContext applicationContext,
      String name, String url) throws ClientProtocolException, IOException {
    InputData inputData;
    if (args.length == 3) {
      inputData = new InputData();
      String[] dataItems = args[2].split(",");
      inputData.setCactiDataItems(dataItems);
      inputData.setName(name);
      inputData.setUrl(url);
    } else {
      JmxInputProcessor jmxProcessor = applicationContext.getBean(JmxInputProcessor.class);
      inputData = jmxProcessor.getInputDataFromJmx(name, url);
    }
    return inputData;
  }

  public static void printUsage() {
    System.out.println("Usage1: <Template Name> <Valid Cacti Url>");
    System.out.println("Usage2: <Template Name> <Cacti Url> <comma separated list of data items>");
    System.out
        .println("Usage1 example: \"my service stats\" \"http://localhost:1243/cacti/value/attribute/cacti_apiCounterMBean/AttributeMap/\"");
    System.out
        .println(" The url should be reachable, peyote will do a HTTP GET and create the template based on the result.");
    System.out
        .println("Usage2 example: \"my service stats\" \"http://<host>:1234/cacti/value/attribute/cacti_apiCounterMBean/AttributeMap/\" count1,count2,count3");
    System.out
        .println(" The url will be used for the data input method and the data items will be used in the template");
    System.out.println("It will create a datatemplate.xml that can be imported into cacti");
    System.out.println("Graph templates are not supported yet.");
    System.exit(1);
  }
}
