package com.gyt.shiyan456;

public class ClassCounting extends Counting {
    private int sG;
    private int dG;
    private int aG;

    public ClassCounting(int sG, int dG, String name) {
        super(name);
        this.sG = sG;
        this.dG = dG;
    }

    public ClassCounting(int sG, int dG, int aG, String name) {
        super(name);
        this.sG = sG;
        this.dG = dG;
        this.aG = aG;
    }

    public void showA(){
        super.setGrade(0.5*aG +0.5*dG);
        System.out.println(super.toString() + "A:" + this.toString());

    }

    public void showB(){
        super.setGrade(0.3*aG +0.5*dG + 0.2*aG);
        System.out.println(super.toString() +"B:" + this.toString());
    }

    @Override
    public String toString() {
        return "ClassCounting{" +
                "sG=" + sG +
                ", dG=" + dG +
                ", aG=" + aG +
                '}';
    }
}
