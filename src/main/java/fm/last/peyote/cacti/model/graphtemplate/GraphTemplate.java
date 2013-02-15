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
package fm.last.peyote.cacti.model.graphtemplate;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("unused")
@XmlType(propOrder = { "name", "graph", "graphTemplateItems", "graphDataSourceInput" })
public class GraphTemplate {

  @XmlElement
  private String name;

  @XmlElement(name = "graph")
  private Graph graph;

  @XmlElementWrapper(name = "items")
  @XmlAnyElement
  private List<JAXBElement<GraphTemplateItem>> graphTemplateItems;

  public void setGraphTemplateItems(List<JAXBElement<GraphTemplateItem>> graphTemplateItems) {
    this.graphTemplateItems = graphTemplateItems;
  }

  @XmlElementWrapper(name = "inputs")
  @XmlAnyElement
  private List<JAXBElement<GraphDataSourceInput>> graphDataSourceInput;

  public void setGraphDataSourceInput(List<JAXBElement<GraphDataSourceInput>> graphDataSourceInput) {
    this.graphDataSourceInput = graphDataSourceInput;
  }

  public void setGraph(Graph graph) {
    this.graph = graph;
  }

  public static class Graph {
    @XmlElement
    private String title;

    public void setTitle(String title) {
      this.title = title;
    }

    @XmlElement(name = "image_format_id")
    private final String imageFormatId = "1";
    @XmlElement
    private final int height = 120;
    @XmlElement
    private final int width = 500;
    @XmlElement(name = "slope_mode")
    private final String slopeMode = "on";
    @XmlElement(name = "auto_scale")
    private final String autoScale = "on";
    @XmlElement(name = "auto_scale_opts")
    private final String autoScaleOpts = "2";
    @XmlElement(name = "auto_scale_log")
    private final String autoScaleLog = "";
    @XmlElement(name = "scale_log_units")
    private final String scaleLogUnits = "";
    @XmlElement(name = "auto_scale_rigid")
    private final String autoScaleRigid = "";
    @XmlElement(name = "auto_padding")
    private final String autoPadding = "on";
    @XmlElement
    private final String export = "on";
    @XmlElement(name = "upper_limit")
    private final int upperLimits = 100;
    @XmlElement(name = "lower_limit")
    private final int lowerLimits = 0;
    @XmlElement(name = "base_value")
    private final int baseValue = 1000;
    @XmlElement(name = "unit_value")
    private final String unitValue = "";
    @XmlElement(name = "unit_exponent_value")
    private final String unitExponentValue = "";
    @XmlElement(name = "vertical_label")
    private String verticalLabel;

    public void setVerticalLabel(String verticalLabel) {
      this.verticalLabel = verticalLabel;
    }
  }

public static class GraphDataSourceInput {

    // <hash_09002170522b3cd02927bf59c8c9ed17748092>
    // <name>Data Source [xFound]</name>
    // <description></description>
    // <column_name>task_item_id</column_name>
    // <items>hash_000021e19aa9660e4664569b90a5b7e6c34592|hash_000021789b4c79bad64b274c62da0b9b6bcc46|hash_00002145398d6a29f780cd131225c271f93008|hash_0000211e73d3365098ebd1ce71990a241c4923</items>
    // </hash_09002170522b3cd02927bf59c8c9ed17748092>
    //   Items are real graphItems except that the hash (from the export) is wrong it should be hash_1000....
    public void setDataSourceName(String itemName) {
      // TODO Auto-generated method stub

    }

    public void setDataInputFieldId(String dataInputFieldId) {
      // TODO Auto-generated method stub

    }

    public void setItems() {
      // TODO Auto-generated method stub

    }
  }

}
