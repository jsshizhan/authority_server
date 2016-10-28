package com.own.authority.domain;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by xiemeilong on 14-11-13.
 */
@NoArgsConstructor
@Component
public class TemplateFormatter {
    private String templatesLocation;

    MustacheFactory factory = new DefaultMustacheFactory();

    HashMap<String,Mustache> templates = new LinkedHashMap<>();

    /**
     * @param templatesLocation 存放模板文件的目录相对于classpath的绝对路径（注意要以/开头）
     */
    public TemplateFormatter(String templatesLocation) {
        this.templatesLocation = templatesLocation;
    }

    /**
     *
     * @param template  模板名，不包含扩展名mustache
     * @param data  POJO或者Map
     * @return  渲染后的字符串
     * @throws IOException
     */
    public String render(String template, Object data) {
        StringWriter writer = new StringWriter();
        getMustache(template).execute(writer,data);
        return writer.toString();
    }

    /**
     *
     * @param template 模板名，不包含扩展名mustache
     * @param data POJO或者Map
     * @param outputStream 将渲染后的数据写入输出流
     * @throws IOException
     */
    public void renderTo(String template, Object data, OutputStream outputStream) {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        getMustache(template).execute(writer,data);
    }

    private Mustache getMustache(String template) {
        if(!templates.containsKey(template)) {
            ClassPathResource classPathResource = new ClassPathResource(Paths.get(templatesLocation,template+".mustache").toString());
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(classPathResource.getInputStream(),"UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage()); //出现这种异常算是bug，所以不要强制让上层调用者处理
            }
            templates.put(template,factory.compile(inputStreamReader,template));
        }

        return templates.get(template);
    }
}
