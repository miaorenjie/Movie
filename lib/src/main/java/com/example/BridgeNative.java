package com.example;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by admin on 2017/5/21.
 */

public class BridgeNative {

    public static final String INT="java.lang.Integer";
    public static final String STRING="java.lang.String";
    public static final String LONG="java.lang.Long";
    public static final String DECIMAL="java.math.BigDecimal";
    public static final String BOOL="java.lang.Boolean";
    public static final String OBJECTS="OS";
    public static final String INTS="IS";
    public static final String STRINGS="SS";
    public static final String FLOAT="java.lang.Float";
    public static final String FLOATS="";

    public static Object send(String type,String method,Object ...params)
    {

        String r=type+"!"+method+"!"+encodeParams(params);
        Object res=null;
        System.out.println("send"+r);
        try {
            Socket s=new Socket("123.207.152.184",8893);
            //Socket s=new Socket("127.0.0.1",8893);
            OutputStream os=s.getOutputStream();

            os.write(r.getBytes());
            //s.shutdownOutput();
            InputStream is=s.getInputStream();
            while (is.available()==0);
            int count=0;
            while (count==0)
                count=is.available();
            byte[] c=new byte[count];
            int readcount=0;
            while((readcount+=is.read(c,readcount,c.length-readcount))!=c.length);
            r=new String(c);
            System.out.println("recv:"+r);
            res=decodeParams(r);
            is.close();
            os.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    public static String encodeParams(Object ...params)
    {
        if (params==null||params.length==0)
        {
            return "";
        }
        JsonArray param=new JsonArray();
        for (int i=0;i<params.length;i++)
        {
            if (params[i] instanceof float[])
            {
                JsonArray x=new JsonArray();
                for (int j=0;j<((float[])params[i]).length;j++)
                {
                    x.add(new JsonString().setValue(((float[])params[i])[j]+""));
                }
                param.add(new JsonString().setValue(FLOATS));
                param.add(x);
            }else
            if (params[i] instanceof int[])
            {
                JsonArray x=new JsonArray();
                for (int j=0;j<((int[])params[i]).length;j++)
                {
                    x.add(new JsonString().setValue(((int[])params[i])[j]+""));
                }
                param.add(new JsonString().setValue(INTS));
                param.add(x);
            }else
            if (params[i] instanceof String[])
            {
                JsonArray x=new JsonArray();
                for (int j=0;j<((String[])params[i]).length;j++)
                {
                    x.add(new JsonString().setValue(((String[])params[i])[j]));
                }
                param.add(new JsonString().setValue(STRINGS));
                param.add(x);
            }else
            if (params[i] instanceof Object[])
            {
                JsonArray x=new JsonArray();
                for (int j=0;j<((Object[])params[i]).length;j++)
                {
                    x.add(new JsonString().setValue(((Object[])params[i])[j].getClass().getName()));
                    x.add(new JsonString().setValue(((Object[])params[i])[j].toString()));
                }
                param.add(new JsonString().setValue(OBJECTS));
                param.add(x);
            }else
            {
                param.add(new JsonString().setValue(params[i].getClass().getName()));

                param.add(new JsonString().setValue(params[i].toString()));
            }

        }

        return param.toString()+"";
    }

    public static Object decodeParams(String params)
    {
        Object[] param=null;
        JsonArray x;
        if (params==null||params.equals("")||params.equals("void"))
            return null;
        try {
            JsonArray ja= JsonParser.parseArray(new StringReader(params));
            param=new Object[ja.size()/2];

            for (int i=0;i<ja.size()/2;i++)
            {
                switch(((JsonString)ja.get(i*2)).getString())
                {
                    case STRINGS:
                        x=(JsonArray) ja.get(i*2+1);
                        String []ss=new String[x.size()];

                        for (int j=0;j<ss.length;j++)
                        {
                            ss[j]=((JsonString)x.get(j)).getString();
                        }
                        param[i]=ss;
                        break;
                    case INTS:
                        x=(JsonArray) ja.get(i*2+1);
                        int []is=new int[x.size()];

                        for (int j=0;j<is.length;j++)
                        {
                            is[j]=Integer.decode(((JsonString)x.get(j)).getString());
                        }
                        param[i]=is;
                        break;
                    case FLOATS:
                        x=(JsonArray) ja.get(i*2+1);
                        float []fs=new float[x.size()];

                        for (int j=0;j<fs.length;j++)
                        {
                            fs[j]=Float.valueOf(((JsonString)x.get(j)).getString());
                        }
                        param[i]=fs;
                        break;
                    case OBJECTS:
                        x=(JsonArray) ja.get(i*2+1);
                        Object []os=new Object[x.size()/2];

                        for (int j=0;j<os.length;j++)
                        {
                            String type=((JsonString)x.get(j*2)).getString();
                            String r=((JsonString)x.get(j*2+1)).getString();

                            switch (type)
                            {
                                case INT:
                                    os[j]=Integer.decode(r);
                                    break;
                                case "java.sql.Timestamp":
                                case STRING:
                                    os[j]=r;
                                    break;
                                case DECIMAL:
                                case FLOAT:
                                    os[j]=Float.valueOf(r);
                                    break;
                                case LONG:
                                    os[j]=Long.decode(r);
                                    break;

                                case BOOL:
                                    os[j]=Boolean.parseBoolean(r);
                                    break;
                            }
                        }
                        param[i]=os;
                        break;

                    case INT:
                        param[i]=Integer.decode(((JsonString)ja.get(i*2+1)).getString());
                        break;
                    case FLOAT:
                        param[i]=Float.valueOf(((JsonString)ja.get(i*2+1)).getString());
                        break;
                    case STRING:
                        param[i]=((JsonString)ja.get(i*2+1)).getString();
                        break;
                    case LONG:
                        param[i]=Long.decode(((JsonString)ja.get(i*2+1)).getString());
                        break;
                    case DECIMAL:
                        param[i]=new BigDecimal(((JsonString)ja.get(i*2+1)).getString());
                        break;
                    case BOOL:
                        param[i]=Boolean.parseBoolean(((JsonString)ja.get(i*2+1)).getString());
                        break;
                }
            }
        } catch (IOException e) {
        }

        if (param.length>=1&&
                (param[0] instanceof Object[]||param[0] instanceof Integer[]||param[0] instanceof String[]||param[0] instanceof Float[]))
        {
            ArrayList r=new ArrayList();
            for (int i=0;i<param.length;i++)
            {
                ArrayList c=new ArrayList(((Object[])param[i]).length);
                r.add(c);

                for (int j=0;j<((Object[])param[i]).length;j++)
                {
                    c.add(((Object[])param[i])[j]);
                }
            }
            return r;
        }else if (param.length==1)
        {
            return param[0];
        }

        return null;
    }

}
