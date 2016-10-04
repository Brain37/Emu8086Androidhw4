package cs.cuw.com.emu8086;

import java.util.Arrays;

/**
 * Created by awesomefat on 9/27/16.
 */

public abstract class Storage
{
    protected String name;
    protected int size;

    public String toHex(String val)
    {
        val = val.toLowerCase();
        boolean negative = false;
        if(val.charAt(0) == '-')
        {
            negative = true;
        }

        try
        {
            if (negative)
            {
                return twosComplementHex(val, "decimal");
            }
            else
            {
                return Integer.toHexString(Integer.parseInt(val));
            }
        }
        catch(Exception e)
        {
            //must be something other than decimal or a decimal with a d at the end
            char suffix = val.charAt(val.length()-1);
            if(suffix == 'd' && !val.startsWith("0x"))
            {
                if (negative)
                {
                    return (String) twosComplementHex(val, "decimald");
                }
                else
                {
                    return Integer.toHexString(Integer.parseInt(val.substring(0, val.length() - 1)));
                }
            }
            else if(suffix == 'b' && !val.startsWith("0x"))
            {
                if (negative)
                {
                    return twosComplementHex(val, "binary");
                }
                else
                {
                    return Integer.toHexString(Integer.parseInt(val.substring(0, val.length() - 1), 2));
                }
            }
            else if(suffix == 'o')
            {
                if (negative)
                {
                    return twosComplementHex(val, "octal");
                }
                else
                {
                    return Integer.toHexString(Integer.parseInt(val.substring(0, val.length() - 1), 8));
                }
            }
            else
            {
                if(negative)
                {
                    return twosComplementHex(val, "hex");
                }
                val = val.replace("h","");
                val = val.replace("x", "");
                return val;
            }
        }
    }

    public String twosComplementHex(String val, String numberFormat)
    {
        String temp = val.substring(1);
        if(numberFormat.equals("decimal"))
        {
            return Integer.toHexString(Integer.parseInt(toTwoComplement(Integer.toBinaryString(Integer.parseInt(temp))),2));
        }
        else if(numberFormat.equals("binary"))
        {
            return Integer.toHexString(Integer.parseInt(toTwoComplement(temp),2));
        }
        else if(numberFormat.equals("decimald"))
        {
            return Integer.toHexString(Integer.parseInt(toTwoComplement(Integer.toBinaryString(Integer.parseInt(temp.substring(0, temp.length() - 1)))),2));
        }
        else if(numberFormat.equals("octal"))
        {
            return Integer.toHexString(Integer.parseInt(toTwoComplement(Integer.toBinaryString(Integer.parseInt(temp.substring(0, temp.length()-1),8))),2));
        }
        else if(numberFormat.equals("hex"))
        {
            temp = temp.replace("h","");
            temp = temp.replace("x", "");
            return Integer.toHexString(Integer.parseInt(toTwoComplement(Integer.toBinaryString(Integer.parseInt(temp.substring(0, temp.length()-1),16))),2));
        }
        return "";
    }

    public String toTwoComplement(String binaryVal)
    {
        char[] ar = new char[8];
        Arrays.fill(ar, '0');
        //flip it
        for(int i = 0; i < binaryVal.length(); i++)
        {
            if(binaryVal.charAt(i) == '1')
            {
                ar[i] = '0';
            }
            else
            {
                ar[i] = '1';
            }
        }
        //add 1
        boolean carry = true;
        for (int i = 0; i < ar.length; i++)
        {
            if(ar[i] == '0' && !carry)
            {
                ar[i] = '1';
            }
            else if(ar[i] == '0' && carry)
            {
                ar[i] = '1';
                carry = false;
            }
            else if(ar[i] == '1' && carry)
            {
                ar[i] = '0';
            }
        }
        String toReturn = "";
        for(int i = 0; i < ar.length; i++)
        {
            toReturn += ar[i];
        }
        return toReturn;
    }
}
