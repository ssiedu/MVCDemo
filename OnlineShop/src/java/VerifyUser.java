
import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyUser extends HttpServlet {
    Connection con;PreparedStatement ps;ResultSet rs;
    @Override
    public void init() throws ServletException {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "root");
        String qr="select uname from userinfo where userid=? and password=?";
        ps=con.prepareStatement(qr);
          
        }catch(Exception e){
            
        }
    }

    @Override
    public void destroy() {
    try{
        con.close();
    }catch(Exception e){
        
    }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String s1=request.getParameter("userid");
        String s2=request.getParameter("password");
        String s3=request.getParameter("usertype");
        if(s3.equals("admin")){
            if(s1.equals("admin") && s2.equals("admin")){
                response.sendRedirect("adminhome.jsp");
               // out.println("Welcome Admin");
            }else{
                out.println("Invalid Admin");
            }
            
        }else{
            try{
            ps.setString(1, s1);
            ps.setString(2, s2);
            rs=ps.executeQuery();
            boolean found=rs.next();
            if(found==true){
                RequestDispatcher rd=request.getRequestDispatcher("buyerhome.jsp");
                rd.forward(request, response);
                //out.println("Welcome Buyer");
            }else{
                out.println("Invalid Buyer");
            }
            
            
            }catch(Exception e){}
        }
        
        
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}