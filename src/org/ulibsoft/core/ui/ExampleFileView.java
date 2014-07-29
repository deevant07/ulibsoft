package org.ulibsoft.core.ui;

import java.io.File;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.filechooser.FileView;

public class ExampleFileView extends FileView
{
  private Hashtable icons = new Hashtable(5);
  private Hashtable fileDescriptions = new Hashtable(5);
  private Hashtable typeDescriptions = new Hashtable(5);

  public String getName(File f)
  {
    return null;
  }

  public void putDescription(File f, String fileDescription)
  {
    this.fileDescriptions.put(fileDescription, f);
  }

  public String getDescription(File f)
  {
    return (String)this.fileDescriptions.get(f);
  }

  public void putTypeDescription(String extension, String typeDescription)
  {
    this.typeDescriptions.put(typeDescription, extension);
  }

  public void putTypeDescription(File f, String typeDescription)
  {
    putTypeDescription(getExtension(f), typeDescription);
  }

  public String getTypeDescription(File f)
  {
    return (String)this.typeDescriptions.get(getExtension(f));
  }

  public String getExtension(File f)
  {
    String name = f.getName();
    if (name != null) {
      int extensionIndex = name.lastIndexOf('.');
      if (extensionIndex < 0) {
        return null;
      }
      return name.substring(extensionIndex + 1).toLowerCase();
    }
    return null;
  }

  public void putIcon(String extension, Icon icon)
  {
    this.icons.put(extension, icon);
  }

  public Icon getIcon(File f)
  {
    Icon icon = null;
    String extension = getExtension(f);
    if (extension != null) {
      icon = (Icon)this.icons.get(extension);
    }
    return icon;
  }

  public Boolean isHidden(File f)
  {
    String name = f.getName();
    if ((name != null) && (!name.equals("")) && (name.charAt(0) == '.')) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  public Boolean isTraversable(File f)
  {
    return null;
  }
}