
import java.util.*;
import java.sql.*;
public class railway {
    public static void main(String[] args) throws Exception{
        Scanner scc=new Scanner(System.in);
        String url="jdbc:mysql://localhost:3306/travel";
        String username="root";
        String password="mysql";
        Connection con=DriverManager.getConnection(url,username,password);
        int number;
        System.out.println("Enter user or admin");
        String name=scc.next();
        if(name.equals("user")){
        do{
            System.out.println("Enter 1.booking/n 2.cancellation /n 3.booked status /n 4.end");
            number=scc.nextInt();
        switch(number){
            case 1:{
                try {
    booking();
} catch (Exception e) {
    // TODO: handle exception
     System.out.println(e);
}    
            }
                break;
            case 2:{ 
                cancellation();
        }
                break;
            case 3:{ 
                System.out.println("Enter id to view details");
                int entry=scc.nextInt();
                Statement st=con.createStatement();
                String q="select * from chennai_to_palani where id="+entry;
                ResultSet r=st.executeQuery(q);
                r.next();
                System.out.println(r.getInt(1));
                System.out.println(r.getString(2));
                System.out.println(r.getString(3));
                System.out.println(r.getString(4));
                }
            break;
            case 4:
            break;
            default:
                break;
        }
        }while(number<4);
    }
        if(name.equals("admin")){
            do{
            System.out.println("Enter 1: to display booked / 2: to display available tickets / 3: to exit");
            number=scc.nextInt();
            switch(number){
        case 1:{
                String query="select * from chennai_to_palani";
                 Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
        }
    }
                break;
            case 2:{
                String query="select * from available";
                 Statement st=con.createStatement();
                 ResultSet rs=st.executeQuery(query);
                 rs.next();
                //while(rs.next()){
                    System.out.println("Upper birth  -->  "+rs.getInt(1));
                    System.out.println("Middle birth  -->  "+rs.getInt(2));
                    System.out.println("Lower birth  -->  "+rs.getInt(3));
                    System.out.println("Rac seats  -->  "+rs.getInt(4));
                    System.out.println("waiting seats  -->  "+rs.getInt(5));
               // }
            }
                break;
                case 3:
                    break;
            default:{
                System.out.println("enter correct number");
                }
        }
    
}while(number<3);
        }
        con.close();
    }
    public static void booking() throws Exception{  
                Scanner scc=new Scanner(System.in);
                int age;
                int count;
                String name,seat;
                String url="jdbc:mysql://localhost:3306/travel";
                String username="root";
                String password="mysql";
                Connection con=DriverManager.getConnection(url,username,password);
                String query="select * from available";
                 Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
                System.out.println("enter age,name,birthpreference");
                age=scc.nextInt();
                while(age<=0||age>150){
                System.out.println("enter valid age");
                age=scc.nextInt();}  
                System.out.println("enter name");
                name=scc.next();
                while(name.length()<=2){
                System.out.println("enter valid name");
                name=scc.next();}
                System.out.println("enter berth preference as upper or lower or middle");
                seat=scc.next();
                while(!(seat.equals("upper")||seat.equals("lower")||seat.equals("middle"))){
                System.out.println("enter valid berth preference");
                seat=scc.next();}
                String q="insert into chennai_to_palani(nam,birth_preferred,birth_alloted) values (?,?,?)";
        PreparedStatement prep=con.prepareStatement(q);
        prep.setString(1, name);
        prep.setString(2, seat);
        if(seat.equals("upper")&&(rs.getInt(1))>1){
            count=rs.getInt(1);
            query="update available set upper="+--count;
            st.executeUpdate(query);
            prep.setString(3, "upper");
        prep.executeUpdate();
            System.out.println("berth preference available");
            System.out.println("upperbirth booked for u");
           
        }
        else if(seat.equals("middle")&&(rs.getInt(2))>1){
            count=rs.getInt(2);
            query="update available set middle="+--count;
            st.executeUpdate(query);
            prep.setString(3, "middle");
        prep.executeUpdate();
            System.out.println("berth preference available");
        System.out.println("middlebirth booked for u");
        }
        else if(seat.equals("lower")&&(rs.getInt(3))>1){
            count=rs.getInt(3);
            query="update available set lower="+--count;
            st.executeUpdate(query);
            prep.setString(3, "lower");
        prep.executeUpdate();
            System.out.println("berth preference available");
        System.out.println("lowerbirth booked for u");
        }
        else if((rs.getInt(1))>1){
            count=rs.getInt(1);
            query="update available set upper="+--count;
            st.executeUpdate(query);
            prep.setString(3, "upper");
        prep.executeUpdate();
            System.out.println("berth preference not available");
        System.out.println("upperbirth booked for u");
        }
        else if((rs.getInt(2))>1){
            count=rs.getInt(2);
            query="update available set middle="+--count;
            st.executeUpdate(query);
            prep.setString(3, "middle");
        prep.executeUpdate();
            System.out.println("berth preference not available");
        System.out.println("middlebirth booked for u");
        }
        else if((rs.getInt(3))>1){
            count=rs.getInt(3);
            query="update available set lower="+--count;
            st.executeUpdate(query);
            prep.setString(3, "lower");
        prep.executeUpdate();
            System.out.println("berth preference not available");
        System.out.println("lowerbirth booked for u");
        }
        else if((rs.getInt(4))>1){
            count=rs.getInt(4);
            query="update available set middle="+--count;
            st.executeUpdate(query);
            prep.setString(3, "RAC");
        prep.executeUpdate();
            System.out.println("berth preference not available");
        System.out.println("rac ticket booked for u");
        }
        else if((rs.getInt(5))>1){
            count=rs.getInt(5);
            query="update available set lower="+--count;
            st.executeUpdate(query);
            prep.setString(3, "Waiting list");
        prep.executeUpdate();
            System.out.println("berth preference not available");
        System.out.println("yor are in waiting list");
        }
        else{
            System.out.println("sorry there are no available seats");
        }

  con.close();  
}
 public static void cancellation() throws Exception{  
                Scanner scc=new Scanner(System.in);
                String seatans="";
                System.out.println("Enter id to cancel");
                int entry=scc.nextInt();
                String url="jdbc:mysql://localhost:3306/travel";
                String username="root";
                String password="mysql";
                Connection con=DriverManager.getConnection(url,username,password);
                Statement st=con.createStatement();
                String q="select birth_alloted from chennai_to_palani where id="+entry;
                ResultSet r=st.executeQuery(q);
                r.next();
                seatans=r.getString(1);
                System.out.println(seatans);
                String query="delete from chennai_to_palani where id="+entry;
                int rs=st.executeUpdate(query);
                String q2="select "+seatans+" from available";
                ResultSet r2=st.executeQuery(q2);
                r2.next();
                int count=r2.getInt(1);
                System.out.println(count);
                String q3="update available set upper="+(++count);
                st.executeUpdate(q3);
                if(rs!=0){ 
                    System.out.println("successfully cancelled");
                    }
                else{   
                    System.out.println("enter valid id");
                    cancellation();
                 }
 } 
}  
   /* if(t.waitingticket==0){
        System.out.println("seats not available");
        return;
    }
    if((p.seat.equals("upper")&&t.upperticket>0)||(p.seat.equals("middle")&&t.middleticket>0)||(p.seat.equals("lower")&&t.lowerticket>0)){
    if(p.seat.equals("upper")&&t.upperticket>0){
        t.book(p,t.upperbirth.get(0),"Upper");
        System.out.println("berth preference available");
        System.out.println("upperbirth booked for u");
        t.upperticket--;
        t.upperbirth.remove(0);
    } 
    else if(p.seat.equals("middle")&&t.middleticket>0){
        ticketboker.book(p,ticketboker.middlebirth.get(0),"middle");
        System.out.println("berth preference available");
        System.out.println("middlebirth booked for u");
        t.middlebirth.remove(0);
        t.middleticket--;
    }
    else if(p.seat.equals("lower")&&t.lowerticket>0){
        t.book(p,t.lowerbirth.get(0),"lower");
        System.out.println("berth preference available");
        System.out.println("lowerbirth booked for u");
        t.lowerbirth.remove(0);
        t.lowerticket--;
    }}
    else if(t.upperticket>0){
        
        System.out.println(p.seat);
        t.book(p,t.upperbirth.get(0),"Upper");
        System.out.println("berth preference not available");
        System.out.println("upperbirth booked for u");
        t.upperticket--;
        t.upperbirth.remove(0);
    }
    else if(t.middleticket>0){
        t.book(p,t.middlebirth.get(0),"middle");
        System.out.println("berth preference not available");
        System.out.println("middlebirth booked for u");
        t.middlebirth.remove(0);
        t.middleticket--;
    }
    else if(t.lowerticket>0){
        t.book(p,t.lowerbirth.get(0),"lower");
        System.out.println("berth preference not available");
        System.out.println("lowerbirth booked for u");
        t.lowerbirth.remove(0);
        t.lowerticket--;
    }
    else if(t.racticket>0){
        t.rac(p,t.racseat.get(0),"rac");
        System.out.println("berth preference not available");
        System.out.println("rac ticket booked for u");
        t.racseat.remove(0);
        t.racticket--;
    }
    else if(t.waitingticket>0){
        t.waiting(p,t.waitingseat.get(0),"waiting");
        System.out.println("berth preference not available");
        System.out.println("waiting ticket booked for u");
        t.waitingseat.remove(0);
        t.waitingticket--;
    }*/  



