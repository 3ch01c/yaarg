package edu.nmt.cs.itweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession session;

    /**
     *
     * Processes requests for both HTTP GET & POST methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected synchronized void processRequest(HttpServletRequest request,
HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        try
		{
                ArrayList<Button> buttons = new ArrayList<Button>();
                    //in theory, we'll get buttons from the database or some algorithm.
                    //here, they're static, but they're coming from a servlet via ajax
                    //rather than being static in the html page itself
                    if(request.getParameter("type").equals("navigation"))
                    {
                        buttons.add(new Button("Fight"));
                        buttons.add(new Button("Inventory"));
                        buttons.add(new Button("Quest"));
                        buttons.add(new Button("Home"));
                    }
                    else if(request.getParameter("type").equals("fightRequest"))
                    {
                        buttons.add(new Button("PVP"));
                        buttons.add(new Button("PVE"));
                        buttons.add(new Button("Cancel"));
                    }
                    else if(request.getParameter("type").equals("Fight"))
                    {
                        buttons.add(new Button("Skills"));
                        buttons.add(new Button("Inventory"));
                        buttons.add(new Button("Run"));
                    }

                    else if(request.getParameter("type").equals("Skills"))
                    {
                        buttons = buildSkillButtons(buttons);
                        buttons.add(new Button("Back"));
                    }

                out.print(buildButtonHtml(buttons));

        }
		catch(Exception e)
		{
			out.println("Exception: "+e);
		}
		finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP GET method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ButtonServlet";
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Button> buildSkillButtons(ArrayList<Button> skillButtons)
    {
        ArrayList<Skill> skills = (ArrayList<Skill>)session.getAttribute("skillInventory");
        String skillName;
        for(int i = 0; i < skills.size();i++)
        {
            skillName = skills.get(i).getName();
            skillButtons.add(new Button(skillName, "skill"));
        }
        return skillButtons;
    }

    public String buildButtonHtml(ArrayList<Button> btns)
    {
        String tempStr = "";
        int i =0;
        Button currentButton;
        tempStr += "<table align=\"center\" style=\"width: 100%; height: 100%\">";
        for(Iterator<Button> btnIterator = btns.listIterator(); btnIterator.hasNext();)
        {
            currentButton = btnIterator.next();
            if(i == 0)
            {
                tempStr += "<tr>";
            }
            tempStr += "<td>";
            tempStr += currentButton.toHtml();
            tempStr += "</td>";
            i+=1;
            if(i%2 == 0)
            {
                tempStr += "</tr>";
                i = 0;
            }
        }
        tempStr += "</table>";
        return tempStr;
    }

}