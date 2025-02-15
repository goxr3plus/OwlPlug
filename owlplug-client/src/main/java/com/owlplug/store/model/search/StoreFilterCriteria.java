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
 
package com.owlplug.store.model.search;

import java.util.Objects;
import javafx.scene.image.Image;

public class StoreFilterCriteria {

  private Object value;
  private String textValue;
  private StoreFilterCriteriaType filterType;
  private Image icon;

  /**
   * Creates a StoreFilterCriteria.
   * 
   * @param value      - critria value
   * @param filterType - criteria type
   */
  public StoreFilterCriteria(Object value, StoreFilterCriteriaType filterType) {
    super();
    this.value = value;
    this.filterType = filterType;
  }

  /**
   * Creates a StoreFilterCriteria.
   * 
   * @param value      - criteria value
   * @param filterType - criteria type
   * @param icon       - criteria icon displayed
   */
  public StoreFilterCriteria(Object value, StoreFilterCriteriaType filterType, Image icon) {
    super();
    this.value = value;
    this.filterType = filterType;
    this.icon = icon;
  }

  /**
   * Creates a StoreFilterCriteria.
   * 
   * @param value      - criteria value
   * @param filterType - criteria type
   * @param icon       - criteria icon to display
   * @param textValue  - custom text value overwriting original value toString()
   *                   conversion.
   */
  public StoreFilterCriteria(Object value, StoreFilterCriteriaType filterType, Image icon, String textValue) {
    super();
    this.value = value;
    this.icon = icon;
    this.filterType = filterType;
    this.textValue = textValue;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public StoreFilterCriteriaType getFilterType() {
    return filterType;
  }

  public void setFilterType(StoreFilterCriteriaType filterType) {
    this.filterType = filterType;
  }

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

  @Override
  public String toString() {
    if (textValue != null) {
      return textValue;
    }
    return value.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoreFilterCriteria criteria = (StoreFilterCriteria) o;
    return Objects.equals(value, criteria.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
