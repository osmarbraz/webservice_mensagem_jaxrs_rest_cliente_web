
import com.servico.MensagemServico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author osmar
 */
public class MensagemServlet extends HttpServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MensagemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MensagemServlet </h1>");
            //Recupera o objeto do serviço            
            MensagemServico mensagemServico = new MensagemServico();
            //Altera a mensagem
            mensagemServico.setMensagem(request.getParameter("mensagem"));
            out.println("Mensagem alterada!");
            out.println("<br><br><a href=index.jsp>Voltar</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
