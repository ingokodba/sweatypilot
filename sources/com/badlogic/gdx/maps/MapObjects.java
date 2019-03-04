package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapObjects implements Iterable<MapObject> {
    private Array<MapObject> objects = new Array();

    public MapObject get(int index) {
        return (MapObject) this.objects.get(index);
    }

    public MapObject get(String name) {
        int n = this.objects.size;
        for (int i = 0; i < n; i++) {
            MapObject object = (MapObject) this.objects.get(i);
            if (name.equals(object.getName())) {
                return object;
            }
        }
        return null;
    }

    public void add(MapObject object) {
        this.objects.add(object);
    }

    public void remove(int index) {
        this.objects.removeIndex(index);
    }

    public void remove(MapObject object) {
        this.objects.removeValue(object, true);
    }

    public int getCount() {
        return this.objects.size;
    }

    public <T extends MapObject> Array<T> getByType(Class<T> type) {
        return getByType(type, new Array());
    }

    public <T extends MapObject> Array<T> getByType(Class<T> type, Array<T> fill) {
        fill.clear();
        int n = this.objects.size;
        for (int i = 0; i < n; i++) {
            MapObject object = (MapObject) this.objects.get(i);
            if (ClassReflection.isInstance(type, object)) {
                fill.add(object);
            }
        }
        return fill;
    }

    public Iterator<MapObject> iterator() {
        return this.objects.iterator();
    }
}
