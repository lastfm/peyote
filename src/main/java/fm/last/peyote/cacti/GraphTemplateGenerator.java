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

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import fm.last.peyote.cacti.hash.HashKeyGenerator;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate.Graph;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplate.GraphDataSourceInput;
import fm.last.peyote.cacti.model.graphtemplate.GraphTemplateItem;
import fm.last.peyote.cacti.model.graphtemplate.RootGraphTemplate;

@Component
public class GraphTemplateGenerator {

  private final HashKeyGenerator hashKeyGenerator;

  @Autowired
  public GraphTemplateGenerator(HashKeyGenerator hashKeyGenerator) {
    this.hashKeyGenerator = hashKeyGenerator;
  }

  public RootGraphTemplate generateRootGraphTemplate(InputData inputData) {
    RootGraphTemplate rootGraphTemplate = new RootGraphTemplate(inputData.getName(), hashKeyGenerator);
    rootGraphTemplate.setGraphTemplate(generateGraphTemplate(inputData));
    return rootGraphTemplate;
  }

  private GraphTemplate generateGraphTemplate(InputData inputData) {
    GraphTemplate result = new GraphTemplate();
    Graph graph = new Graph();
    graph.setTitle("|host_description| - "+inputData.getName());
    graph.setVerticalLabel("requests/second");
    result.setGraph(graph);
    result.setGraphDataSourceInput(generateGraphDataSourceInputs(inputData));
    result.setGraphTemplateItems(generateGraphTemplateItems());
    return result;
  }

  private List<JAXBElement<GraphDataSourceInput>> generateGraphDataSourceInputs(InputData inputData) {
    List<JAXBElement<GraphDataSourceInput>> result = Lists.newArrayList();
    for (String itemName : inputData.getJmxItems()) {
      result.add(generateGraphDataSourceInput(itemName, inputData));
    }
    return result;
  }
  
  private JAXBElement<GraphDataSourceInput> generateGraphDataSourceInput(String itemName, InputData inputData) {
    GraphDataSourceInput result = new GraphDataSourceInput();
    result.setDataSourceName(itemName);
    result.setDataInputFieldId(hashKeyGenerator.getDataInputFieldId(hashKeyGenerator.getDataTemplateId(inputData.getName()), itemName));
    result.setItems();
    JAXBElement<GraphDataSourceInput> element = new JAXBElement<GraphDataSourceInput>(new QName(hashKeyGenerator.getGraphDataSourceInputId()), GraphDataSourceInput.class, result);
    return element;
  }
  private List<JAXBElement<GraphTemplateItem>> generateGraphTemplateItems() {
    // TODO Auto-generated method stub
    return null;
  }

}
