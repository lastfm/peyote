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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class JmxInputProcessor {

  public InputData getInputDataFromJmx(String name, String url) throws ClientProtocolException, IOException {
    InputData result = new InputData();
    result.setName(name);
    result.setUrl(convertToCactiUrl(url));
    result.setCactiDataItems(fetchCactiDataItemsFromUrl(url));
    return result;
  }

  private String convertToCactiUrl(String urlString) throws MalformedURLException {
    URL url = new URL(urlString);
    return url.getProtocol()+"://<host>:" + url.getPort() + url.getPath();
  }

  private String[] fetchCactiDataItemsFromUrl(String url) throws ClientProtocolException, IOException {
    String content = Request.Get(url).execute().returnContent().asString();
    String[] keyValues = content.split(" ");
    List<String> result = Lists.newArrayList();
    for (String keyValue : keyValues) {
      result.add(keyValue.split(":")[0]);
    }
    return result.toArray(new String[0]);
  }

}
