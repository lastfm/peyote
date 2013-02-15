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
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("unused")
@XmlType(propOrder = { "name", "dataSource", "dataTemplateItems", "dataTemplateInputItems" })
public class DataTemplate {

  @XmlElement
  private String name;

  @XmlElement(name = "ds")
  private DataSource dataSource;

  @XmlElementWrapper(name = "items")
  @XmlAnyElement
  private List<JAXBElement<DataTemplateItem>> dataTemplateItems;

  @XmlElementWrapper(name = "data")
  @XmlAnyElement
  private List<JAXBElement<DataTemplateInputItem>> dataTemplateInputItems;

  public void setName(String name) {
    this.name = name;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void setDataTemplateItems(List<JAXBElement<DataTemplateItem>> dataTemplateItems) {
    this.dataTemplateItems = dataTemplateItems;
  }

  public void setDataTemplateInputItems(List<JAXBElement<DataTemplateInputItem>> dataTemplateInputItems) {
    this.dataTemplateInputItems = dataTemplateInputItems;
  }

  public static class DataSource {// This is the same default for all dataTemplates
    
    @XmlElement(name = "t_name")
    private final String tName = "";

    @XmlElement
    private String name;

    public void setName(String name) {
      this.name = name;
    }

    @XmlElement(name = "data_input_id")
    private String dataInputId;

    public void setDataInputId(String dataInputId) {
      this.dataInputId = dataInputId;
    }

    @XmlElement(name = "t_rra_id")
    private final String tRraId = "";

    @XmlElement(name = "t_rrd_step")
    private final String tRrdStep = "";

    @XmlElement(name = "rrd_step")
    private final int rrdStep = 300;

    @XmlElement(name = "t_active")
    private final String tActive = "";

    @XmlElement(name = "active")
    private final String active = "on";

    //These RRA_ITEMS come standard with cacti when you import no need to set anything here
    @XmlElement(name = "rra_items")
    private final String rraItems = "";
  }

  public static class DataTemplateItem {

    @XmlElement(name = "t_data_source_name")
    private final String tDataSourceName = "";

    @XmlElement(name = "data_source_name")
    private String dataSourceName;

    public void setDataSourceName(String dataSourceName) {
      this.dataSourceName = dataSourceName;
    }

    @XmlElement(name = "t_rrd_minimum")
    private final String tRrdMinimum = "";

    @XmlElement(name = "rrd_minimum")
    private final int rrdMinimum = 0;

    @XmlElement(name = "t_rrd_maximum")
    private final String tRrdMaximum = "";

    @XmlElement(name = "rrd_maximum")
    private final int rrdMaximum = 10000;

    @XmlElement(name = "t_data_source_type_id")
    private final String tDataSourceTypeId = "";

    @XmlElement(name = "data_source_type_id")
    private final int dataSourceTypeId = 2; // 1=GAUGE, 2=COUNTER, 3=DERIVE, 4=ABSOLUTE

    @XmlElement(name = "t_rrd_heartbeat")
    private final String tRrdHeartbeat = "";

    @XmlElement(name = "rrd_heartbeat")
    private final int rrdHeartbeat = 600;

    @XmlElement(name = "t_data_input_field_id")
    private final String tDataInputFieldId = "";

    @XmlElement(name = "data_input_field_id")
    private String dataInputFieldId;

    public void setDataInputFieldId(String dataInputFieldId) {
      this.dataInputFieldId = dataInputFieldId;
    }
  }

  public static class DataTemplateInputItem {

    @XmlElement(name = "data_input_field_id")
    private String dataInputFieldId;
    @XmlElement(name = "t_value")
    private final String tValue = "";
    @XmlElement(name = "value")
    private final String value = "";

    public void setDataInputFieldId(String dataInputFieldId) {
      this.dataInputFieldId = dataInputFieldId;
    }

  }

}
