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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import fm.last.peyote.cacti.hash.HashKeyGenerator;
import fm.last.peyote.cacti.model.datatemplate.DataInputMethod;
import fm.last.peyote.cacti.model.datatemplate.DataInputMethod.DataInputField;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate.DataSource;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate.DataTemplateInputItem;
import fm.last.peyote.cacti.model.datatemplate.DataTemplate.DataTemplateItem;
import fm.last.peyote.cacti.model.datatemplate.RootDataTemplate;

@Component
public class DataTemplateGenerator {

  private String dataTemplateId;
  private String[] dataTemplateItemNames;
  private final HashKeyGenerator cactiHashKeyGenerator;
  private final List<String> dataInputFieldIds = new ArrayList<String>();

  @Autowired
  public DataTemplateGenerator(HashKeyGenerator cactiHashKeyGenerator) {
    this.cactiHashKeyGenerator = cactiHashKeyGenerator;
  }

  public RootDataTemplate generateRootDataTemplate(InputData inputData) {
    String name = inputData.getName();
    dataTemplateItemNames = inputData.getJmxItems();
    dataTemplateId = cactiHashKeyGenerator.getDataTemplateId(name);
    RootDataTemplate rootDataTemplate = new RootDataTemplate(name, cactiHashKeyGenerator);
    rootDataTemplate.setDataInputMethod(generateDataInputMethod(name, inputData.getUrl()));
    rootDataTemplate.setDataTemplate(generateDataTemplate(name));// generate this last, it needs dataInPutFieldIds.
    return rootDataTemplate;
  }

  private DataTemplate generateDataTemplate(String name) {
    DataTemplate result = new DataTemplate();
    result.setName(name);
    result.setDataSource(generateDataSource(name));
    result.setDataTemplateItems(generateDataTemplateItems());
    result.setDataTemplateInputItems(generateDataTemplateInputItems());
    return result;
  }

  private DataInputMethod generateDataInputMethod(String name, String url) {
    DataInputMethod result = new DataInputMethod();
    result.setName("Get " + name);
    result.setInputString("curl \"" + url + "\"");
    result.setDataInputFields(generateDataInputFields());
    return result;
  }

  private List<JAXBElement<DataInputField>> generateDataInputFields() {
    List<JAXBElement<DataInputField>> result = Lists.newArrayList();
    result.add(generateDataHostNameInputField());
    for (String itemName : dataTemplateItemNames) {
      result.add(generateDataHostNameOutputField(itemName));
    }
    return result;
  }

  private JAXBElement<DataInputField> generateDataHostNameInputField() {
    String friendlyName = "Hostname";
    String inputFieldId = cactiHashKeyGenerator.getDataInputFieldId(dataTemplateId, friendlyName);
    dataInputFieldIds.add(inputFieldId);// add it here it needs to be linked later
    DataInputField dataInputField = new DataInputField();
    dataInputField.setName(friendlyName);
    dataInputField.setTypeCode("hostname");
    dataInputField.setInputOutput("in");
    dataInputField.setDataName("host");
    JAXBElement<DataInputField> element = new JAXBElement<DataInputField>(new QName(inputFieldId),
        DataInputField.class, dataInputField);
    return element;
  }

  private JAXBElement<DataInputField> generateDataHostNameOutputField(String itemName) {
    String inputFieldId = cactiHashKeyGenerator.getDataInputFieldId(dataTemplateId, itemName);
    DataInputField dataInputField = new DataInputField();
    dataInputField.setName(itemName);
    dataInputField.setUpdateRra("on");
    dataInputField.setInputOutput("out");
    dataInputField.setDataName(itemName);
    JAXBElement<DataInputField> element = new JAXBElement<DataInputField>(new QName(inputFieldId),
        DataInputField.class, dataInputField);
    return element;
  }

  private DataSource generateDataSource(String name) {
    DataSource result = new DataSource();
    result.setName("|host_description| - " + name);
    result.setDataInputId(cactiHashKeyGenerator.getDataInputMethodId(name));
    return result;
  }

  private List<JAXBElement<DataTemplateItem>> generateDataTemplateItems() {
    List<JAXBElement<DataTemplateItem>> result = Lists.newArrayList();
    for (String itemName : dataTemplateItemNames) {
      JAXBElement<DataTemplateItem> element = new JAXBElement<DataTemplate.DataTemplateItem>(new QName(
          cactiHashKeyGenerator.getDataTemplateItem(dataTemplateId, itemName)), DataTemplateItem.class,
          generateDataTemplateItem(itemName));
      result.add(element);
    }
    return result;
  }

  private DataTemplateItem generateDataTemplateItem(String itemName) {
    DataTemplateItem result = new DataTemplateItem();
    result.setDataSourceName(itemName);
    result.setDataInputFieldId(cactiHashKeyGenerator.getDataInputFieldId(dataTemplateId, itemName));
    return result;
  }

  private List<JAXBElement<DataTemplateInputItem>> generateDataTemplateInputItems() {
    List<JAXBElement<DataTemplateInputItem>> result = Lists.newArrayList();
    int i = 0;
    for (String dataInputFieldId : dataInputFieldIds) {
      String elementName = "item_" + String.format("%03d", i++);
      JAXBElement<DataTemplateInputItem> element = new JAXBElement<DataTemplate.DataTemplateInputItem>(new QName(
          elementName), DataTemplateInputItem.class, generateDataTemplateInputItem(dataInputFieldId));
      result.add(element);
    }
    return result;
  }

  private DataTemplateInputItem generateDataTemplateInputItem(String dataInputFieldId) {
    DataTemplateInputItem result = new DataTemplateInputItem();
    result.setDataInputFieldId(dataInputFieldId);
    return result;
  }
}
