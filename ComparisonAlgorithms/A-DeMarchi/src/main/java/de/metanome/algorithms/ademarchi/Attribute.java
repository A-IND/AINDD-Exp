package de.metanome.algorithms.ademarchi;

import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.input.TableInputGenerator;


import java.beans.ConstructorProperties;


class Attribute {
  private final int id;
  private final String tableName;
  private final String name;
  private final String type;
  private final RelationalInputGenerator relationalInputGenerator;
  private final TableInputGenerator tableInputGenerator;

  public RelationalInputGenerator selectInputGenerator() {
    return (RelationalInputGenerator)(this.tableInputGenerator != null ? this.tableInputGenerator : this.relationalInputGenerator);
  }

  @ConstructorProperties({"id", "tableName", "name", "type", "relationalInputGenerator", "tableInputGenerator"})
  Attribute(int id, String tableName, String name, String type, RelationalInputGenerator relationalInputGenerator, TableInputGenerator tableInputGenerator) {
    this.id = id;
    this.tableName = tableName;
    this.name = name;
    this.type = type;
    this.relationalInputGenerator = relationalInputGenerator;
    this.tableInputGenerator = tableInputGenerator;
  }

  public static AttributeBuilder builder() {
    return new AttributeBuilder();
  }

  public int getId() {
    return this.id;
  }

  public String getTableName() {
    return this.tableName;
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public RelationalInputGenerator getRelationalInputGenerator() {
    return this.relationalInputGenerator;
  }

  public TableInputGenerator getTableInputGenerator() {
    return this.tableInputGenerator;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Attribute)) {
      return false;
    } else {
      Attribute other = (Attribute)o;
      if (!other.canEqual(this)) {
        return false;
      } else if (this.getId() != other.getId()) {
        return false;
      } else {
        label73: {
          Object this$tableName = this.getTableName();
          Object other$tableName = other.getTableName();
          if (this$tableName == null) {
            if (other$tableName == null) {
              break label73;
            }
          } else if (this$tableName.equals(other$tableName)) {
            break label73;
          }

          return false;
        }

        Object this$name = this.getName();
        Object other$name = other.getName();
        if (this$name == null) {
          if (other$name != null) {
            return false;
          }
        } else if (!this$name.equals(other$name)) {
          return false;
        }

        label59: {
          Object this$type = this.getType();
          Object other$type = other.getType();
          if (this$type == null) {
            if (other$type == null) {
              break label59;
            }
          } else if (this$type.equals(other$type)) {
            break label59;
          }

          return false;
        }

        Object this$relationalInputGenerator = this.getRelationalInputGenerator();
        Object other$relationalInputGenerator = other.getRelationalInputGenerator();
        if (this$relationalInputGenerator == null) {
          if (other$relationalInputGenerator != null) {
            return false;
          }
        } else if (!this$relationalInputGenerator.equals(other$relationalInputGenerator)) {
          return false;
        }

        Object this$tableInputGenerator = this.getTableInputGenerator();
        Object other$tableInputGenerator = other.getTableInputGenerator();
        if (this$tableInputGenerator == null) {
          if (other$tableInputGenerator != null) {
            return false;
          }
        } else if (!this$tableInputGenerator.equals(other$tableInputGenerator)) {
          return false;
        }

        return true;
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof Attribute;
  }

  public int hashCode() {
    //int PRIME = true;
    int result = 1;
    result = result * 59 + this.getId();
    Object $tableName = this.getTableName();
    result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
    Object $name = this.getName();
    result = result * 59 + ($name == null ? 43 : $name.hashCode());
    Object $type = this.getType();
    result = result * 59 + ($type == null ? 43 : $type.hashCode());
    Object $relationalInputGenerator = this.getRelationalInputGenerator();
    result = result * 59 + ($relationalInputGenerator == null ? 43 : $relationalInputGenerator.hashCode());
    Object $tableInputGenerator = this.getTableInputGenerator();
    result = result * 59 + ($tableInputGenerator == null ? 43 : $tableInputGenerator.hashCode());
    return result;
  }

  public String toString() {
    return "Attribute(id=" + this.getId() + ", tableName=" + this.getTableName() + ", name=" + this.getName() + ", type=" + this.getType() + ", relationalInputGenerator=" + this.getRelationalInputGenerator() + ", tableInputGenerator=" + this.getTableInputGenerator() + ")";
  }

  public static class AttributeBuilder {
    private int id;
    private String tableName;
    private String name;
    private String type;
    private RelationalInputGenerator relationalInputGenerator;
    private TableInputGenerator tableInputGenerator;

    AttributeBuilder() {
    }

    public AttributeBuilder id(int id) {
      this.id = id;
      return this;
    }

    public AttributeBuilder tableName(String tableName) {
      this.tableName = tableName;
      return this;
    }

    public AttributeBuilder name(String name) {
      this.name = name;
      return this;
    }

    public AttributeBuilder type(String type) {
      this.type = type;
      return this;
    }

    public AttributeBuilder relationalInputGenerator(RelationalInputGenerator relationalInputGenerator) {
      this.relationalInputGenerator = relationalInputGenerator;
      return this;
    }

    public AttributeBuilder tableInputGenerator(TableInputGenerator tableInputGenerator) {
      this.tableInputGenerator = tableInputGenerator;
      return this;
    }

    public Attribute build() {
      return new Attribute(this.id, this.tableName, this.name, this.type, this.relationalInputGenerator, this.tableInputGenerator);
    }

    public String toString() {
      return "Attribute.AttributeBuilder(id=" + this.id + ", tableName=" + this.tableName + ", name=" + this.name + ", type=" + this.type + ", relationalInputGenerator=" + this.relationalInputGenerator + ", tableInputGenerator=" + this.tableInputGenerator + ")";
    }
  }
}
