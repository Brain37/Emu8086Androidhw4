package cs.cuw.com.emu8086;

import java.util.Arrays;

/**
 * Created by awesomefat on 9/27/16.
 */

public class GeneralPurposeRegister extends Storage
{
    private String highName;
    private String lowName;
    private char[] value;

    public GeneralPurposeRegister(String name, int size, String highName, String lowName)
    {
        this.name = name;
        this.size = size;
        this.highName = highName;
        this.lowName = lowName;
        this.value = new char[this.size/4];
        Arrays.fill(this.value,'0');
    }

    public boolean hasHighSubRegister(String name)
    {
        return name.equalsIgnoreCase(this.highName);
    }

    public boolean hasLowSubRegister(String name)
    {
        return name.equalsIgnoreCase(this.lowName);
    }

    public String getValue()
    {
        return new String(this.value);
    }

    public void load(String val)
    {
        String hexVal = this.toHex(val);
        int decVal = Integer.parseInt(hexVal, 16);
        if(decVal < Math.pow(2,this.size) && decVal >= (Math.pow(2,this.size-1)*-1))
        {
            int endPos = hexVal.length()-1;
            for(int i = this.value.length-1; i >= 0 && endPos >= 0; i--)
            {
                this.value[i] = hexVal.charAt(endPos);
                endPos--;
            }
        }
        else
        {
            System.err.println("Value too big...what should we do???");
        }
    }

    public void loadHigh(String val)
    {
        String hexVal = this.toHex(val);
        int decVal = Integer.parseInt(hexVal, 16);
        if(decVal < Math.pow(2,this.size/2) && decVal >= (Math.pow(2,this.size/2-1)*-1))
        {
            int endPos = hexVal.length()-1;
            for(int i = this.value.length/2-1; i >= 0 && endPos >= 0; i--)
            {
                this.value[i] = hexVal.charAt(endPos);
                endPos--;
            }
        }
        else
        {
            System.err.println("Value too big...what should we do???");
        }
    }

    public void loadLow(String val)
    {
        String hexVal = this.toHex(val);
        int decVal = Integer.parseInt(hexVal, 16);
        if(decVal < Math.pow(2,this.size/2) && decVal >= (Math.pow(2,this.size/2-1)*-1))
        {
            int endPos = hexVal.length()-1;
            for(int i = this.value.length-1; i >= this.value.length/2 && endPos >= 0; i--)
            {
                this.value[i] = hexVal.charAt(endPos);
                endPos--;
            }
        }
        else
        {
            System.err.println("Value too big...what should we do???");
        }
    }
}
