//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.util;

import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.ColumnPermutation;
import de.metanome.algorithm_integration.results.InclusionDependency;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InclusionDependencyBuilder {
    private ColumnPermutation dependent;
    private ColumnPermutation referenced;

    public InclusionDependencyBuilder() {
    }

    public InclusionDependency build() {
        return new InclusionDependency(this.dependent, this.referenced);
    }

    public static ColumnPermutationBuilder dependent() {
        return new ColumnPermutationBuilder(new InclusionDependencyBuilder());
    }

    public static class ColumnPermutationBuilder {
        private final InclusionDependencyBuilder outer;
        private final List<ColumnIdentifier> columns;

        ColumnPermutationBuilder(InclusionDependencyBuilder outer) {
            this.outer = outer;
            this.columns = new ArrayList();
        }

        public ColumnPermutationBuilder column(String relationName, String columnName) {
            this.columns.add(new ColumnIdentifier(relationName, columnName));
            return this;
        }

        public ColumnPermutationBuilder columns(String relationName, List<String> columnNames) {
            Iterator var3 = columnNames.iterator();

            while(var3.hasNext()) {
                String columnName = (String)var3.next();
                this.columns.add(new ColumnIdentifier(relationName, columnName));
            }

            return this;
        }

        public ColumnPermutationBuilder referenced() {
            this.outer.dependent = this.columnPermutation();
            return new ColumnPermutationBuilder(this.outer);
        }

        public InclusionDependency build() {
            this.outer.referenced = this.columnPermutation();
            return this.outer.build();
        }

        private ColumnPermutation columnPermutation() {
            return new ColumnPermutation((ColumnIdentifier[])this.columns.toArray(new ColumnIdentifier[this.columns.size()]));
        }
    }
}
