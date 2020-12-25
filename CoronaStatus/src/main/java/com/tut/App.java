package com.tut;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sun.plugin.net.protocol.jar.CachedJarURLConnection;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class App
{
    public static String getData(String c) throws Exception
    {
        StringBuffer br=new StringBuffer();
        br.append("<html>"+"<body style='text-align:center;color:red;'>");
        br.append(c.toUpperCase());
        br.append("<br>");
        br.append("<br>");
        
        String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
        CachedJarURLConnection jsoup;
        Document doc= Jsoup.connect(url).get();

        Elements elements=doc.select("#maincounter-wrap");

        elements.forEach((e) -> {
            String text=e.select("h1").text();
            String count=e.select(".maincounter-number>span").text();
            br.append(text).append(" : ").append(count).append("<br>");
        });
        br.append("</body>"+"</html>");
      return br.toString();
    }

    public static void main( String[] args ) throws Exception
    {


        JFrame frame=new JFrame("Details of Country");
        frame.setSize(500,500);



        Font f=new Font("Poppins",Font.BOLD,30);

        JTextField text=new JTextField();

        JLabel data=new JLabel();

        text.setFont(f);
        data.setFont(f);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        data.setHorizontalAlignment(SwingConstants.CENTER);

        JButton button=new JButton("Get Details");

        button.addActionListener(event -> {
            try {
                String maindata = text.getText();
                String result = getData(maindata);
                data.setText(result);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
        button.setFont(f);

        frame.setLayout(new BorderLayout());

        frame.add(text,BorderLayout.NORTH);
        frame.add(data,BorderLayout.CENTER);
        frame.add(button,BorderLayout.SOUTH);

        frame.setVisible(true);


    }
}
