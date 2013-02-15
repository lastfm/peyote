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
package fm.last.peyote.cacti.model.datatemplate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import fm.last.peyote.cacti.hash.HashKeyGenerator;

@XmlRootElement(name = "cacti")
@XmlType(propOrder = { "dataTemplate", "dataInputMethod" })
public class RootDataTemplate {

  private DataTemplate dataTemplate;
  private DataInputMethod dataInputMethod;
  private String name;
  private HashKeyGenerator cactiHashKeyGenerator;

  public RootDataTemplate(String name, HashKeyGenerator cactiHashKeyGenerator) {
    this.name = name;
    this.cactiHashKeyGenerator = cactiHashKeyGenerator;
  }

  // For Jaxb
  @SuppressWarnings("unused")
  private RootDataTemplate() {
  }

  @XmlAnyElement
  public JAXBElement<DataTemplate> getDataTemplate() {
    return new JAXBElement<DataTemplate>(new QName(cactiHashKeyGenerator.getDataTemplateId(name)), DataTemplate.class,
        dataTemplate);
  }

  public void setDataTemplate(DataTemplate dataTemplate) {
    this.dataTemplate = dataTemplate;
  }

  @XmlAnyElement
  public JAXBElement<DataInputMethod> getDataInputMethod() {
    return new JAXBElement<DataInputMethod>(new QName(cactiHashKeyGenerator.getDataInputMethodId(name)),
        DataInputMethod.class, dataInputMethod);
  }

  public void setDataInputMethod(DataInputMethod dataInputMethod) {
    this.dataInputMethod = dataInputMethod;
  }

}
