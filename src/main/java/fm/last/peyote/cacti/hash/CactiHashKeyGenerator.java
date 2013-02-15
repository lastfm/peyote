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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;


public class CactiHashKeyGenerator implements HashKeyGenerator {

  // The cacti hash is parsed by cacti (php code) like this:
  // 837 function parse_xml_hash($hash) {
  // 838 if (preg_match("/hash_([a-f0-9]{2})([a-f0-9]{4})([a-f0-9]{32})/", $hash, $matches)) {
  // 839 $parsed_hash["type"] = check_hash_type($matches[1]);
  // 840 $parsed_hash["version"] = strval(check_hash_version($matches[2]));
  // 841 $parsed_hash["hash"] = $matches[3];
  // 842
  // 843 /* an error has occured */
  // 844 if (($parsed_hash["type"] === false) || ($parsed_hash["version"] === false)) {
  // 845 return false;
  // 846 }

  public final static Map<String, String> VERSION_CODES_TO_HASH = new HashMap<String, String>();
  static {
    VERSION_CODES_TO_HASH.put("0.8.7g", "0021");
    VERSION_CODES_TO_HASH.put("0.8.7h", "0022");
  }

  private final Map<String, String> idCache = new HashMap<String, String>();

  private final String cactiVersion;

  public CactiHashKeyGenerator(String cactiVersion) {
    this.cactiVersion = cactiVersion;
    if (!VERSION_CODES_TO_HASH.containsKey(cactiVersion)) {
      throw new IllegalArgumentException("Can't generate for cacti version: " + cactiVersion);
    }
  }

  @Override
  public String getDataTemplateId(String name) {
    return getHash(name, CactiType.DATA_TEMPLATE);
  }

  @Override
  public String getDataInputMethodId(String name) {
    return getHash(name, CactiType.DATA_INPUT_METHOD);
  }

  @Override
  public String getDataTemplateItem(String templateId, String itemName) {
    return getHash(templateId + "_" + itemName, CactiType.DATA_TEMPLATE_ITEM);
  }

  @Override
  public String getDataInputFieldId(String templateId, String itemName) {
    return getHash(templateId + "_" + itemName, CactiType.DATA_INPUT_FIELD);
  }

  @Override
  public String getGraphTemplateId(String name) {
    return getHash(name, CactiType.GRAPH_TEMPLATE);
  }

  private String getHash(String name, CactiType cactiType) {
    String key = name + cactiType.getTypeId();
    String result = idCache.get(key);
    if (result == null) {
      result = generateHash(cactiType.getTypeId());
      idCache.put(key, result);
    }
    return result;
  }

  protected String generateHash(String type) {
    String md5Hash = DigestUtils.md2Hex("peyote" + System.nanoTime());
    return "hash_" + type + getVersion() + md5Hash;
  }

  public String getVersion() {
    return VERSION_CODES_TO_HASH.get(cactiVersion);
  }

  @Override
  public String getGraphDataSourceInputId() {
    // TODO Auto-generated method stub
    return null;
  }


}
