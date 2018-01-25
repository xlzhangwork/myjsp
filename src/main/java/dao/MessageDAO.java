package dao;

import entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public List<Message> queryMessage(String command,String description){

        String url = "jdbc:mysql://localhost:3306/dbspringboot?useUnicode=true&characterEncoding=UTF-8";
        String user = "xlzhang";
        String password = "xlzhang";
        List<Message> messageList = new ArrayList<Message>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");

            List<String> paramList = new ArrayList<String>();
            if(command != null && !"".equals(command.trim())){
                sql.append(" and COMMAND = ?");
                paramList.add(command);
            }
            if(description != null && !"".equals(description.trim())){
                sql.append(" and DESCRIPTION like '%' ? '%' ");
                paramList.add(description);
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());

            for(int i = 0; i< paramList.size(); i++){
                statement.setString(i+1,paramList.get(i));
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getInt("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
                messageList.add(message);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
