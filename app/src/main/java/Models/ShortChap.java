package Models;



public class ShortChap {

    private String name;
    private String ref;

    public ShortChap(String name,String ref) {
        this.name = name;
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
