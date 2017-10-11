package uk.ac.ebi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.service.FormatterService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by chojnasm on 08/06/2016.
 */

@RestController
public class MyController {

    private final FormatterService formatterService;
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Autowired
    public MyController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }

    @RequestMapping(value = "/sampledata")
    public
    void getSampleData(HttpServletResponse httpResponse){
        httpResponse.setHeader("content-disposition", "attachment; filename=sample.txt");

        try {
            IOUtils.copy(getClass().getResourceAsStream("/sample.txt"), httpResponse.getOutputStream());
            httpResponse.flushBuffer();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
