package entities;

public class Missionary {

	String missionary_name;
	int missionary_id;
	
	public Missionary(int m_id, String name)
	{
		missionary_name = name;
		missionary_id = m_id;
	}

	public String getMissionaryName()
	{
		return missionary_name;
	}
	public int getMissionaryID()
	{
		return missionary_id;
	}
	
	public void setMissionaryName(String name)
	{
		missionary_name = name;
	}
	public void setMissionaryID(int m_id)
	{
		missionary_id=m_id;
	}
	public String toString()
	{
		return ""+ missionary_id + " 		|		 " +missionary_name;
	}
}
