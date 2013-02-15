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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CactiHashKeyGeneratorTest {
  
  private static final int MD5HEX_LENGTH = 32;

  private HashKeyGenerator generator = new CactiHashKeyGenerator("0.8.7g");

  @Test
  public void testDataTemplateId() {
    String templateId = generator.getDataTemplateId("ProjectX");
    assertTrue(templateId.startsWith("hash_010021"));
    assertEquals(("hash_010021".length()+MD5HEX_LENGTH), templateId.length());
    String templateId2 = generator.getDataTemplateId("ProjectX");
    assertEquals(templateId, templateId2);
  }
  
  @Test
  public void testDataInputMethod() {
    String templateId = generator.getDataInputMethodId("ProjectX");
    assertTrue(templateId.startsWith("hash_030021"));
    assertEquals(("hash_030021".length()+MD5HEX_LENGTH), templateId.length());
    String templateId2 = generator.getDataInputMethodId("ProjectX");
    assertEquals(templateId, templateId2);
  }
  
  @Test
  public void testDataTemplateItem() {
    String templateId = generator.getDataTemplateItem("ProjectX", "item1");
    assertTrue(templateId.startsWith("hash_080021"));
    assertEquals(("hash_080021".length()+MD5HEX_LENGTH), templateId.length());
    String templateId2 = generator.getDataTemplateItem("ProjectX", "item1");
    assertEquals(templateId, templateId2);
  }
  
  @Test
  public void testDataInputFieldId() {
    String templateId = generator.getDataInputFieldId("ProjectX", "field1");
    assertTrue(templateId.startsWith("hash_070021"));
    assertEquals(("hash_070021".length()+MD5HEX_LENGTH), templateId.length());
    String templateId2 = generator.getDataInputFieldId("ProjectX", "field1");
    assertEquals(templateId, templateId2);
  }
  
}
