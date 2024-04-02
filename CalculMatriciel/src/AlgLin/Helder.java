package AlgLin;


public class Helder extends SysLin {

    public Helder(Matrice m, AlgLin.Vecteur v) throws  IrregularSysLinException  {
        super(m, v);
    }

    public void factorLDR() throws IrregularSysLinException  {
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, secondMembre);
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, secondMembre);

        Matrice.verif_produit(matriceSystem, L.matriceSystem);
        Matrice.produit(matriceSystem, L.matriceSystem);
        Matrice.verif_produit(matriceSystem, D.matriceSystem);
        Matrice.produit(matriceSystem, D.matriceSystem);
        Matrice.verif_produit(matriceSystem, R.matriceSystem);
        Matrice.produit(matriceSystem, R.matriceSystem);
    }

    @Override
    public AlgLin.Vecteur resolution() throws IrregularSysLinException {
        factorLDR();
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, L.resolution());
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, D.resolution());

        return R.resolution();
    }

    public AlgLin.Vecteur resolutionPartielle() throws IrregularSysLinException {
        SysTriangInfUnite L = new SysTriangInfUnite(matriceSystem, secondMembre);
        SysDiagonal D = new SysDiagonal(matriceSystem, L.resolution());
        SysTriangSupUnite R = new SysTriangSupUnite(matriceSystem, D.resolution());

        return R.resolution();
    }

    public void setSecondMembre(AlgLin.Vecteur SecondMembre) {
        this.secondMembre = SecondMembre;
    }

    public static void main(String[] args) throws IrregularSysLinException {
        Matrice mat1 = new Matrice("src/resources/matrice.txt");
        Matrice mat2 = new Matrice("src/resources/matrice1.txt");
        AlgLin.Vecteur vec1 = new AlgLin.Vecteur("src/resources/vecteur.txt");
        Matrice matRes = new Matrice(mat1.nbLigne(), mat1.nbColonne());
        System.out.println("Matrice 1 : \n" + mat1);
        System.out.println("Matrice 2 : \n" + mat2);
        System.out.println("Vecteur : \n" + vec1);
        Helder H = new Helder(mat1,vec1);
        System.out.println("Résultat de Ax = b: " + H.resolution());

        Matrice.verif_produit(mat1, mat2);
        matRes = Matrice.produit(mat1,mat2);
        System.out.println("Resultat de A² :");
        System.out.println(matRes);

        System.out.println("Résultat de A² x= b");
        Helder Hresu = new Helder(matRes,vec1);
        System.out.println(Hresu.resolution());


    }
}