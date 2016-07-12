package edu.nmt.cs.itweb;

//import java.net.*; // Commented out to remove warning (it's never read locally)
import java.sql.*;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

public class BattleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * Processes requests for both HTTP GET & POST methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private Connection connection;
	private Statement statement;
	@SuppressWarnings("unused")
	private Exception exception;
	@SuppressWarnings("unused")
	private HashMap<String, GameCharacter> characterMap;

	public void init(ServletConfig servletconfig)
	throws ServletException
	{
		super.init(servletconfig);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//What is this DB connection? If this should point to the normal
			//Database, it needs to be using
			//Connection con = DBConnection.getConnection();

			String urlString = "jdbc:mysql://localhost:3306/testBase";
			String username = "root";
			String password = "testpass";

			this.connection = DriverManager.getConnection(urlString, username, password);
			this.statement = connection.createStatement();

			this.characterMap = new HashMap<String, GameCharacter>();
		}
		catch(Exception e)
		{
			this.exception = e;
		}
	}

	protected synchronized void processRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			HttpSession session = request.getSession();
			String requestType = request.getParameter("type");
			if(requestType != null)
			{
				if(requestType.equals("fight"))
				{
					getServletContext().getRequestDispatcher("/battleContainer.jsp").forward(request, response);
				}
				if(requestType.equals("initialize"))
				{
					getServletContext().getRequestDispatcher("/battleContainer.jsp").forward(request, response);
				}
				else if(requestType.equals("attack"))
				{
					String userId = request.getParameter("uid");
					ResultSet rs = this.statement.executeQuery("select * from characters where userId = \'"+userId+"\'");
					rs.next();
					for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
					{
						out.print(rs.getString(i));
						out.print(",");
					}
				}
				else if(requestType.equals("use"))
				{
					String userId = request.getParameter("uid");
					ResultSet rs = this.statement.executeQuery("select * from characters where userId = \'"+userId+"\'");
					rs.next();
					for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
					{
						out.print(rs.getString(i));
						out.print(",");
					}
				}
				else if(requestType.equals("run"))
				{
					String userId = request.getParameter("uid");
					ResultSet rs = this.statement.executeQuery("select * from characters where userId = \'"+userId+"\'");
					rs.next();
					for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
					{
						out.print(rs.getString(i));
						out.print(",");
					}
				}
                                else if(requestType.equals("whoGoesFirst"))
                                {
                                    GameBattle battle = (GameBattle)session.getAttribute("battle");
                                    if(battle.getPlayer1().getAttack_speed() >= battle.getPlayer2().getAttack_speed())
                                    {
                                        battle.setWhoseTurn(battle.getPlayer1().getId());
                                    }
                                    else
                                    {
                                        battle.setWhoseTurn(battle.getPlayer2().getId());
                                    }

                                    getServletContext().getRequestDispatcher("/GameServlet?type=processTurn").forward(request, response);
                                }
                                else if(requestType.equals("continueBattle"))
                                {
                                    Skill skill = (Skill)request.getAttribute("skill");
                                    GameBattle battle = (GameBattle)session.getAttribute("battle");
                                    //decide attacker/def in order to pass to getDamage
                                    GameCharacter attacker;
                                    GameCharacter defender;
                                    if(battle.getPlayer1().getId() == battle.whoseTurn())
                                    {
                                        attacker = battle.getPlayer1();
                                        defender = battle.getPlayer2();
                                    }
                                    else
                                    {
                                        attacker = battle.getPlayer2();
                                        defender = battle.getPlayer1();
                                    }

                                    dmg damage = Damage.getDamage(attacker, defender, skill);
                                    defender.setCurrent_hp(defender.getCurrent_hp() - damage.damage);

                                    String battleMsg = "";
                                    boolean isOver = false;

                                    if(defender.getCurrent_hp() >= 1)
                                    {
                                        battleMsg += "normal";
                                    }
                                    else
                                    {
                                        battleMsg += "over";
                                        isOver = true;
                                    }

                                    battleMsg += ","+attacker.getId();

                                    battleMsg += ","+attacker.getCurrent_hp()+","+attacker.getCurrent_energy()+","+defender.getCurrent_hp()+","+defender.getCurrent_energy();
                                    battleMsg += ","+damage.crit+","+skill.getImage()+","+skill.getName()+","+damage.attacks;
                                    battleMsg += ","+battle.getTurn();

                                    if(isOver)
                                    {
                                        Item randomItem = new Item();

                                        battleMsg += ","+attacker.getId();
                                        int xpGained = Level.Up(attacker, defender);
                                        battleMsg += ","+xpGained+","+defender.getGold();
                                        battleMsg += ","+randomItem.getName()+","+randomItem.getIcon();
                                    }

                                    battle.setBattleMessage(battleMsg);

                                    battle.incrementTurn();

                                    getServletContext().getRequestDispatcher("/GameServlet?type=processTurn").forward(request, response);
                                }
			}
			else
			{
				out.print("Servlet Expecting a Type");
			}
		}
		catch(Exception e)
		{
                    out.println("requestType: "+request.getParameter("type"));
                    out.println("Exception: "+e+"");
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
		return "gameServlet";
	}

}

/*
class Character
{
	private int hp, attack, defense;

	public Character()
	{
		Character(100, 5, 5);
	}

	public Character(int h, int a, int d)
	{

	}
}
 */