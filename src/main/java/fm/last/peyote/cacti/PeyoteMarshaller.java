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

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fm.last.peyote.cacti.model.datatemplate.DataInputMethod;
import fm.last.peyote.cacti.model.datatemplate.DataInputMethod.DataInputField;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate.DataTemplateInputItem;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate.DataTemplateItem;
import fm.last.peyote.cacti.model.datatemplate.RootDataTemplate;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate.Graph;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate.GraphDataSourceInput;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplateItem;
import fm.last.peyote.cacti.model.graphtemplate.RootGraphTemplate;

@Component
public class PeyoteMarshaller {

  private final DataTemplateGenerator dataGenerator;
  private final GraphTemplateGenerator graphGenerator;
  private InputData inputData;

  @Autowired
  public PeyoteMarshaller(DataTemplateGenerator dataGenerator, GraphTemplateGenerator graphGenerator) {
    this.dataGenerator = dataGenerator;
    this.graphGenerator = graphGenerator;
  }

  public void setInputData(InputData inputData) {
    this.inputData = inputData;
  }

  public void generateCactiDataTemplate(Writer outWriter) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(RootDataTemplate.class, DataInputMethod.class, DataTemplate.class,
        DataTemplateItem.class, DataTemplateInputItem.class, DataInputField.class);
    Marshaller marshaller = context.createMarshaller();
    RootDataTemplate rootDataTemplate = dataGenerator.generateRootDataTemplate(inputData);
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(rootDataTemplate, outWriter);
  }

  public void generateCactiGraphTemplate(Writer outWriter) throws JAXBException {
    JAXBContext context = JAXBContext.newInstance(RootGraphTemplate.class, GraphTemplate.class, Graph.class, GraphDataSourceInput.class, GraphTemplateItem.class);
    Marshaller marshaller = context.createMarshaller();
    RootGraphTemplate rootGraphTemplate = graphGenerator.generateRootGraphTemplate(inputData);
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(rootGraphTemplate, outWriter);
  }
}
