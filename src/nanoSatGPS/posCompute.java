package nanoSatGPS;

import Jama.Matrix;

/**
 * Created by Roi on 1/28/2015.
 */
public class posCompute {

    private void setGu(){
        double rao[] = new double[sateliteData.size()];

        for (int i = 0; i < rao.length; i++) {
            rao[i] = Math.sqrt(Math.pow(gu[0]-satelitesXYZ[i][0], POW)+Math.pow(gu[1]-satelitesXYZ[i][1], POW)+Math.pow(gu[2]-satelitesXYZ[i][2], POW));
        }
        double alpha[][] = creatAlpha(sateliteData.size());

        double erro=1;
        while(erro>0.01){
            for (int i = 0; i < alpha.length; i++) {
                for (int j = 0; j < 3; j++) {
                    alpha[i][j] = (gu[j]-satelitesXYZ[i][j])/(rao[i]);
                }
            }
            double[] drao = new double[sateliteData.size()];
            setDrao(drao, rao, pseudoRanges, bu);

            Matrix a = new Matrix(alpha);
            a=a.inverse();
            double[][] pseudoInverse_Alpha = a.getArray();

            double[] dl = multMatrixToVector(pseudoInverse_Alpha, drao);

            bu+=dl[dl.length-1];

            for (int i = 0; i < 3; i++) {
                gu[i]+=dl[i];
            }

            erro = Math.pow(dl[0], POW)+Math.pow(dl[1], POW)+Math.pow(dl[2], POW);


            for (int i = 0; i < rao.length; i++) {
                rao[i] = Math.sqrt(Math.pow(gu[0]-satelitesXYZ[i][0], POW)+Math.pow(gu[1]-satelitesXYZ[i][1], POW)+Math.pow(gu[2]-satelitesXYZ[i][2], POW));
            }
        }
    }
}
