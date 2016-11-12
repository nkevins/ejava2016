package epod.web;

import epod.business.PodBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(location="/tmp", 
        fileSizeThreshold=1024*512, 
        maxFileSize=1024*1024*4, 
        maxRequestSize=1024*1024*4*3)
public class Upload extends HttpServlet {
    
    @EJB private PodBean podBean;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        Integer podId = new Integer(new String(readPart(req.getPart("podId"))));
        String note = new String(readPart(req.getPart("note")));
        byte[] img = readPart(req.getPart("image"));
        
        podBean.uploadPod(podId, note, img);
    }
    
    private byte[] readPart(Part p) throws IOException {
        byte[] buffer = new byte[1024 * 8];
        int sz = 0; 
        try (InputStream is = p.getInputStream()) { 
            BufferedInputStream bis = new BufferedInputStream(is); 
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) { 
                while (-1 != (sz = bis.read(buffer))) 
                    baos.write(buffer, 0, sz); 
                buffer = baos.toByteArray(); 
            } 
        }
        return (buffer);
    }
}
