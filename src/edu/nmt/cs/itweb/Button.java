/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nmt.cs.itweb;

/**
 *
 * @author ddumas
 */
public class Button {
    private String value;
    private String name;

    Button()
    {
        this.value="";
        this.name="";
    }
    Button(String val)
    {
        this.value=val;
        this.name="";
    }
    Button(String val, String newName)
    {
        this.value = val;
        this.name = newName;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String val)
    {
        this.value = val;
    }

    public String toHtml()
    {
        String buttonString = "<input type=\"button\" value=\""+this.value+"\"  onclick=\"buttonPressed(this);\" ";
        if(!this.name.equals(""))
        {
            buttonString += "name=\""+this.name+"\" ";
        }
        buttonString += "></input>";
        return buttonString;
    }
}