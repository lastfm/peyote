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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import fm.last.peyote.cacti.hash.HashKeyGenerator;

@RunWith(MockitoJUnitRunner.class)
public class PeyoteMarshallerTest {

  @Test
  public void testGenerateXml() throws Exception {
    HashKeyGenerator hashGenerator = new DummyCactiHashKeyGenerator("0.8.7g");
    InputData inputData = new InputData();
    inputData.setUrl("http://<host>:8888/cacti/value/attribute/countXsMBean/AttributeMap/");
    inputData.setName("service getX requests");
    inputData.setCactiDataItems(new String[] { "xError", "xFound", "xNotFound" });
    PeyoteMarshaller peyote = new PeyoteMarshaller(new DataTemplateGenerator(hashGenerator),
        new GraphTemplateGenerator(hashGenerator));
    peyote.setInputData(inputData);

    Writer writer = new StringWriter();
    peyote.generateCactiDataTemplate(writer);
    String dataTemplate = writer.toString();
    String xmlDataOriginal = IOUtils.toString(new FileReader(new File(
        "src/test/data/cacti_data_template_service_x_requests.xml")));
    assertEquals(xmlDataOriginal, dataTemplate);

    // TODO PD implement graph template, this will test it
//     writer = new StringWriter();
//     peyote.generateCactiGraphTemplate(writer);
//     String graphTemplate = writer.toString();
//     String xmlGraphOriginal = IOUtils.toString(new FileReader(new File(
//     "src/test/data/cacti_graph_template_service_x_requests.xml")));
//     assertEquals(xmlGraphOriginal, graphTemplate);
  }

}
