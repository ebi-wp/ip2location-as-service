package uk.ac.ebi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uk.ac.ebi.service.FormatterService;
import uk.ac.ebi.Utils;
import uk.ac.ebi.model.IP2Model;
import uk.ac.ebi.service.FormatterService.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by chojnasm on 11/06/2016.
 */

@Controller
public class FormUploadController {

    private final FormatterService formatterService;
    private static final Logger log = LoggerFactory.getLogger(FormUploadController.class);

    @Value("${server.contextPath}")
    String contextPath;


    @Autowired
    public FormUploadController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String provideUploadInfo(Model model) {

        model.addAttribute("fields", IP2Model.getAllFields());
        model.addAttribute("outputFormats", Utils.listOfOutputFormats());
        model.addAttribute("contextPath", contextPath);

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public
    @ResponseBody
    void handleFileUpload(@RequestParam(value = "input", required = false) String inputText,
                          @RequestParam(value = "file", required = false) Object fileObject,
                          @RequestParam(value = "outputFormat", defaultValue = "CSV") String outputFormat,
                          @RequestParam(value = "fields", defaultValue = "all") String[] fields,
                          @RequestParam(value = "csvHeader", defaultValue = "true") boolean csvHeader,
                          @RequestParam(value = "csvDelimiter", defaultValue = ",") Character csvDelimiter,
                          @RequestParam(value = "removeduplicate", defaultValue = "true") boolean ifRemoveDuplicate,
                          HttpServletResponse httpResponse,
                          HttpServletRequest request) {

        // Start the clock
        long start = System.currentTimeMillis();

        httpResponse.setHeader("content-disposition", "attachment; filename=result." + outputFormat.toLowerCase());
        String formattedOutput = null;
        MultipartFile file = null;

        // This work-around is required as Multipart parameter can not be optional (required=false)
        // @RequestParam(value = "file", required = false) Multipart fileObject,
        if (fileObject != null) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            file = multipartRequest.getFile("file");
        }

        try {
            if (file != null && !file.isEmpty()) {
                byte[] encoded = file.getBytes();
                inputText = new String(encoded, Charset.defaultCharset());
            }

            if (inputText != null && inputText.length() > 0) {
                formattedOutput = formatterService.getFormattedOutput(
                        fields,
                        inputText,
                        OUTPUT_FORMAT.valueOf(outputFormat),
                        csvHeader,
                        csvDelimiter,
                        ifRemoveDuplicate);
            }

            httpResponse.getWriter().write(formattedOutput);
            httpResponse.flushBuffer();

            log.info("Elapsed time: " + (System.currentTimeMillis() - start));

        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
