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

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@SuppressWarnings("unused")
public class DataInputMethod {
  
  @XmlElement
  private String name;
  
  public void setName(String name) {
    this.name = name;
  }

  @XmlElement(name="type_id")
  private int typeId=1; //I think this means "Script/Command"
  @XmlElement(name="input_string")
  private String inputString;
  public void setInputString(String inputString) {
    this.inputString = inputString;
  }
  
  @XmlElementWrapper(name="fields")
  @XmlAnyElement
  private List<JAXBElement<DataInputField>> dataInputFields;

  public void setDataInputFields(List<JAXBElement<DataInputField>> dataInputFields) {
    this.dataInputFields = dataInputFields;
  }

  
  public static class DataInputField {
    @XmlElement
    private String name;
    public void setName(String name) {
      this.name = name;
    }
    @XmlElement(name="update_rra")
    private String updateRra = "";
    public void setUpdateRra(String updateRra) {
      this.updateRra = updateRra;
    }
    @XmlElement(name="regexp_match")
    private final String regexpMatch = "";
    @XmlElement(name="allow_nulls")
    private final String allowNulls = "";
    @XmlElement(name="type_code")
    private String typeCode = "";
    public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
    }
    @XmlElement(name="input_output")
    private String inputOutput;
    public void setInputOutput(String inputOutput) {
      this.inputOutput = inputOutput;
    }
    @XmlElement(name="data_name")
    private String dataName;
    public void setDataName(String dataName) {
      this.dataName = dataName;
    }
  }
}
