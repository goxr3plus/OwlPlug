/* OwlPlug
 * Copyright (C) 2019 Arthur <dropsnorz@gmail.com>
 *
 * This file is part of OwlPlug.
 *
 * OwlPlug is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OwlPlug is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OwlPlug.  If not, see <https://www.gnu.org/licenses/>.
 */
 
package com.owlplug.core.model.platform;

import java.util.ArrayList;
import java.util.List;

public class RuntimePlatform {

  private String tag;
  private OperatingSystem operatingSystem;
  private String arch;

  private ArrayList<RuntimePlatform> compatiblePlatforms;

  public RuntimePlatform(String tag, OperatingSystem operatingSystem, String arch) {
    super();
    this.tag = tag;
    this.operatingSystem = operatingSystem;
    this.arch = arch;

    this.compatiblePlatforms = new ArrayList<>();
    this.compatiblePlatforms.add(this);
  }

  public List<String> getCompatiblePlatformsTags() {
    List<String> platforms = new ArrayList<>();
    platforms.add(this.operatingSystem.getCode());
    for (RuntimePlatform platform : compatiblePlatforms) {
      platforms.add(platform.getTag());
    }
    return platforms;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public OperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(OperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public String getArch() {
    return arch;
  }

  public void setArch(String arch) {
    this.arch = arch;
  }

  public ArrayList<RuntimePlatform> getCompatiblePlatforms() {
    return compatiblePlatforms;
  }

  protected void setCompatiblePlatforms(ArrayList<RuntimePlatform> compatibleEnvironments) {
    this.compatiblePlatforms = compatibleEnvironments;
  }

  @Override
  public String toString() {
    return "RuntimePlatform [tag=" + tag + ", operatingSystem=" + operatingSystem + ", arch=" + arch + "]";
  }

}
