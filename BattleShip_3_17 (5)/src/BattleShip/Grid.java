package BattleShip;

public class Grid {
    GridType Type;
    public Grid(GridType type)
    {
        this.Type=type;
    }

    public GridType getType() {
        return Type;
    }

    public void setType(GridType type) {
        Type = type;
    }

    Grid(){}
}
