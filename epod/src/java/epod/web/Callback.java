package epod.web;

import epod.business.PodBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/callback")
public class Callback extends HttpServlet {
    
    @EJB private PodBean podBean;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        Integer podId = new Integer(req.getParameter("podId"));
        String ackId = req.getParameter("ackId");
        
        podBean.updatePodAckId(podId, ackId);
    }
    
}
