/*
Copyright 2014 Paul Sidnell

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package org.psidnell.omnifocus.model;

import java.util.UUID;

import org.psidnell.omnifocus.ConfigParams;
import org.psidnell.omnifocus.expr.ExprAttribute;
import org.psidnell.omnifocus.expr.ExpressionFunctions;
import org.psidnell.omnifocus.sqlite.SQLiteProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author psidnell
 *
 *         The root class for all nodes in the object tree.
 */
public abstract class NodeImpl extends ExpressionFunctions implements Node {

    protected ConfigParams config;

    protected String name;

    private String id = UUID.randomUUID().toString();

    private int rank;

    private boolean isRoot;

    private boolean marked = false;

    @Override
    @SQLiteProperty
    @ExprAttribute(help = "item name/text.")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @SQLiteProperty(name = "persistentIdentifier")
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    @SQLiteProperty
    public int getRank() {
        return rank;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    @JsonIgnore
    public boolean isRoot() {
        return isRoot;
    }

    @Override
    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NodeImpl other = (NodeImpl) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getType() + ":'" + name + "'";
    }

    @Override
    @JsonIgnore
    public boolean isMarked() {
        return marked;
    }

    @Override
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public void setConfigParams (ConfigParams config) {
        this.config = config;
    }
}
