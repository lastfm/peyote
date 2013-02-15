package fm.last.peyote.cacti.model.graphtemplate;

import javax.xml.bind.annotation.XmlElement;

//<hash_100021e19aa9660e4664569b90a5b7e6c34592>
//  <task_item_id>hash_08002124d329a124255465cf751949a02abd41</task_item_id>
//  <color_id>FFD660</color_id>
//  <alpha>FF</alpha>
//  <graph_type_id>7</graph_type_id>
//  <consolidation_function_id>1</consolidation_function_id>
//  <cdef_id>0</cdef_id>
//  <value></value>
//  <gprint_id>hash_060021e9c43831e54eca8069317a2ce8c6f751</gprint_id>
//  <text_format>xFound</text_format>
//  <hard_return></hard_return>
//  <sequence>1</sequence>
//</hash_100021e19aa9660e4664569b90a5b7e6c34592>
//<hash_100021789b4c79bad64b274c62da0b9b6bcc46>
//  <task_item_id>hash_08002124d329a124255465cf751949a02abd41</task_item_id>
//  <color_id>0</color_id>
//  <alpha>FF</alpha>
//  <graph_type_id>9</graph_type_id>
//  <consolidation_function_id>4</consolidation_function_id>
//  <cdef_id>0</cdef_id>
//  <value></value>
//  <gprint_id>hash_060021e9c43831e54eca8069317a2ce8c6f751</gprint_id>
//  <text_format>Current:</text_format>
//  <hard_return></hard_return>
//  <sequence>2</sequence>
//</hash_100021789b4c79bad64b274c62da0b9b6bcc46>
//<hash_10002145398d6a29f780cd131225c271f93008>
//  <task_item_id>hash_08002124d329a124255465cf751949a02abd41</task_item_id>
//  <color_id>0</color_id>
//  <alpha>FF</alpha>
//  <graph_type_id>9</graph_type_id>
//  <consolidation_function_id>1</consolidation_function_id>
//  <cdef_id>0</cdef_id>
//  <value></value>
//  <gprint_id>hash_060021e9c43831e54eca8069317a2ce8c6f751</gprint_id>
//  <text_format>Average:</text_format>
//  <hard_return></hard_return>
//  <sequence>3</sequence>
//</hash_10002145398d6a29f780cd131225c271f93008>
//<hash_1000211e73d3365098ebd1ce71990a241c4923>
//  <task_item_id>hash_08002124d329a124255465cf751949a02abd41</task_item_id>
//  <color_id>0</color_id>
//  <alpha>FF</alpha>
//  <graph_type_id>9</graph_type_id>
//  <consolidation_function_id>3</consolidation_function_id>
//  <cdef_id>0</cdef_id>
//  <value></value>
//  <gprint_id>hash_060021e9c43831e54eca8069317a2ce8c6f751</gprint_id>
//  <text_format>Maximum:</text_format>
//  <hard_return>on</hard_return>
//  <sequence>4</sequence>
//</hash_1000211e73d3365098ebd1ce71990a241c4923>
@SuppressWarnings("unused")
public class GraphTemplateItem {

  @XmlElement(name = "task_item_id")
  private String taskItemId;

  public void setTaskItemId(String taskItemId) {
    this.taskItemId = taskItemId;
  }

  @XmlElement(name = "color_id")
  private String colorId = "0";

  public void setColorId(String colorId) {
    this.colorId = colorId;
  }

  @XmlElement(name = "alpha")
  private final String alpha = "FF";
  @XmlElement(name = "graph_type_id")
  private String graphTypeId;

  public void setGraphTypeId(String graphTypeId) {
    this.graphTypeId = graphTypeId;
  }

  @XmlElement(name = "consolidation_function_id")
  private String consolidationFunctionId;

  public void setConsolidationFunctionId(String consolidationFunctionId) {
    this.consolidationFunctionId = consolidationFunctionId;
  }

  @XmlElement(name = "cdef_id")
  private final String cdefId = "0";
  @XmlElement
  private final String value = "";
  @XmlElement(name = "gprint_id")
  private String gprintId;

  public void setGprintId(String gprintId) {
    this.gprintId = gprintId;
  }

  @XmlElement(name = "text_format")
  private String textFormat;

  public void setTextFormat(String textFormat) {
    this.textFormat = textFormat;
  }

  @XmlElement(name = "hard_return")
  private String hardReturn = "";

  public void setHardReturn(String hardReturn) {
    this.hardReturn = hardReturn;
  }

  @XmlElement
  private int sequence;

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }
}