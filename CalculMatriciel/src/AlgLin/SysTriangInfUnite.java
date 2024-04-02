package AlgLin;


public class SysTriangInfUnite extends SysTriangInf {
    public static SysTriangInfUnite make(Matrice m, AlgLin.Vecteur v) throws IrregularSysLinException  {
        int  j = 0;
        boolean b = true;
        for(int i = 0; i < m.nbLigne(); i++) {
            if(m.getCoef(i, j) != 1) {
                b = false;
            }
            j++;
        }
        if(b) {
            SysTriangInfUnite sys = new SysTriangInfUnite(m, v);
            return sys;
        }
        return null;
    }

    public SysTriangInfUnite(Matrice m, AlgLin.Vecteur v) throws IrregularSysLinException {
        super(m, v);
    }

    public static void main(String[] args) throws IrregularSysLinException  {
        double[] composants = new double[3];
        composants[0] = 2;
        composants[1] = 5;
        composants[2] = 3;
        AlgLin.Vecteur vecteur = new AlgLin.Vecteur(composants);
        double mat[][]= {{1, 0, 0},{2, 1, 0},{6,4,1}};				//1x + 0y = 4
        Matrice matrice = new Matrice(mat);				//3x + 1y = 5
        SysLin sys = make(matrice, vecteur);
        System.out.println(sys.resolution());
    }
}