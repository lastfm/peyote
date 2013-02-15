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
package fm.last.peyote.cacti.hash;

public enum CactiType {

  DATA_TEMPLATE("01"),
  DATA_INPUT_METHOD("03"),
  DATA_TEMPLATE_ITEM("08"),
  DATA_INPUT_FIELD("07"),
  GRAPH_TEMPLATE("00");

  private String typeId;

  private CactiType(String typeId) {
    this.typeId = typeId;
  }

  public String getTypeId() {
    return typeId;
  }

}

// The following values are from cacti code:
// $hash_type_codes = array(
// "round_robin_archive" => "15",
// "cdef" => "05",
// "cdef_item" => "14",
// "gprint_preset" => "06",
// "data_input_method" => "03",
// "data_input_field" => "07",
// "data_template" => "01",
// "data_template_item" => "08",
// "graph_template" => "00",
// "graph_template_item" => "10",
// "graph_template_input" => "09",
// "data_query" => "04",
// "data_query_graph" => "11",
// "data_query_sv_graph" => "12",
// "data_query_sv_data_source" => "13",
// "host_template" => "02" );
// $hash_version_codes = array(
// "0.8.4" => "0000",
// "0.8.5" => "0001",
// "0.8.5a" => "0002",
// "0.8.6" => "0003",
// "0.8.6a" => "0004",
// "0.8.6b" => "0005",
// "0.8.6c" => "0006",
// "0.8.6d" => "0007",
// "0.8.6e" => "0008",
// "0.8.6f" => "0009",
// "0.8.6g" => "0010",
// "0.8.6h" => "0011",
// "0.8.6i" => "0012",
// "0.8.6j" => "0013",
// "0.8.7" => "0014",
// "0.8.7a" => "0015",
// "0.8.7b" => "0016",
// "0.8.7c" => "0017",
// "0.8.7d" => "0018",
// "0.8.7e" => "0019",
// "0.8.7f" => "0020",
// "0.8.7g" => "0021",
// "0.8.7h" => "0022" );

