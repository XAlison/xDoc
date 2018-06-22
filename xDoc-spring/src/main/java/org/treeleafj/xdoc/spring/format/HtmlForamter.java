package org.treeleafj.xdoc.spring.format;

import org.apache.commons.io.IOUtils;
import org.treeleafj.xdoc.format.Formater;
import org.treeleafj.xdoc.model.ApiModule;
import org.treeleafj.xdoc.utils.JsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leaf on 2017/3/18 0018.
 */
public class HtmlForamter implements Formater {

    @Override
    public String format(List<ApiModule> list) {
        InputStream in = HtmlForamter.class.getResourceAsStream("html.vm");
        if (in != null) {
            try {
                String s = IOUtils.toString(in, "utf-8");

                Map<String, Object> model = new HashMap<>();
                model.put("title", "接口文档");
                model.put("apiModules", list);

                return s.replace("_apis_json", JsonUtils.toJson(model));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(in);
            }
        }
        return "";
    }
}