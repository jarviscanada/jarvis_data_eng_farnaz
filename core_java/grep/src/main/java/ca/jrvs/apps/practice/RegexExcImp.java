package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc{

    @Override
    public boolean matchJpeg(String filename) {
        return filename.matches(".*.jpe?g$");
    }
    @Override
    public boolean matchIp(String ip){
        return ip.matches("^(\\d{1,3}\\.){3}\\d{1,3}$");
    }
    @Override
    public boolean isEmptyLine(String line){
        return line.matches("^[ ]*$");
    }

    public static void main(String[] args){
      RegexExcImp regeximp= new RegexExcImp();
      System.out.println(regeximp.matchIp("192.50.16.1"));
      System.out.println(regeximp.matchIp("192.50.16"));
      System.out.println(regeximp.matchIp("192.0.10.1"));
      System.out.println();
      System.out.println(regeximp.matchJpeg("myname.jpeg"));
      System.out.println(regeximp.matchJpeg("myname.jpg"));
      System.out.println(regeximp.matchJpeg("myname.jpegjp"));
      System.out.println(regeximp.matchJpeg("jpeg"));
      System.out.println();
      System.out.println(regeximp.isEmptyLine(" "));
      System.out.println(regeximp.isEmptyLine("file"));
    }
}

