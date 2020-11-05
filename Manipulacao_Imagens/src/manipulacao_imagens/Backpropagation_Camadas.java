/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacao_imagens;

/**
 *
 * @author Avell
 */
public class Backpropagation_Camadas {
    	//entradas de pixels R G B
	double[] r = new double[500];
	double[] g = new double[500];
	double[] b = new double[500];	
	
	//sinapses para entradas R G B
	double [][] h1 = {{0.85}, {0.33}, {0.74}, {0.75}, {0.88}, {0.7}};
	double [][] h2 = {{0.75}, {0.43}, {0.64}, {0.85}, {0.78}, {0.67}};
	double [][] h3 = {{0.65}, {0.53}, {0.54}, {0.95}, {0.68}, {0.57}};
	
	//Primeira camada de neuronios intermediaria
	double [][] v1 = {{0.22}, {0.8}, {0.44}, {0.6}, {0.54}, {0.065}};
	double [][] v2 = {{0.32}, {0.6}, {0.34}, {0.9}, {0.34}, {0.055}};
	double [][] v3 = {{0.12}, {0.9}, {0.24}, {0.8}, {0.24}, {0.75}};
	double [][] v4 = {{0.42}, {0.4}, {0.54}, {0.3}, {0.14}, {0.95}};
	double [][] v5 = {{0.52}, {0.3}, {0.64}, {0.2}, {0.44}, {0.15}};
	double [][] v6 = {{0.62}, {0.5}, {0.74}, {0.1}, {0.84}, {0.35}};
	double [][] v0 = {{0.9}, {0.8}, {0.63}, {0.65}, {0.76}, {0.72}};
	
	//Segunda camada de neuronios intermediaria
	double [][] w1 = {{0.82}, {0.9}, {0.64}, {0.8}, {0.4}, {0.25}};
	double [][] w2 = {{0.72}, {0.6}, {0.44}, {0.7}, {0.9}, {0.15}};
	double [][] w3 = {{0.62}, {0.8}, {0.54}, {0.6}, {0.64}, {0.35}};
	double [][] w4 = {{0.92}, {0.1}, {0.74}, {0.9}, {0.83}, {0.65}};
	double [][] w5 = {{0.02}, {0.6}, {0.34}, {0.3}, {0.72}, {0.75}};
	double [][] w6 = {{0.12}, {0.5}, {0.24}, {0.2}, {0.14}, {0.85}};
	double [][] w0 = {{0.5}, {0.7}, {0.33}, {0.6}, {0.86}, {0.62}};
	
	//Saidas
	double[] y1 = new double[500];
	double[] y2 = new double[500];
	double[] y3 = new double[500];
	double[] y4 = new double[500];
	double[] y5 = new double[500];
	double[] y6 = new double[500];
	double [][] y0 = {{0.2074}, {0.5074}, {0.3074}, {0.1074}, {0.74}, {0.0974}, {0.6174}};
	
	//Função de ativação das saidas
	double[] y1f = new double[500];
	double[] y2f = new double[500];
	double[] y3f = new double[500];
	double[] y4f = new double[500];
	double[] y5f = new double[500];
	double[] y6f = new double[500];
	
	//Derivadas das saidas
	double[] y1ff = new double[500];
	double[] y2ff = new double[500];
	double[] y3ff = new double[500];
	double[] y4ff = new double[500];
	double[] y5ff = new double[500];
	double[] y6ff = new double[500];
	
	//Erros
	double[] erro1 = new double[500];
	double[] erro2 = new double[500];
	double[] erro3 = new double[500];
	double[] erro4 = new double[500];
	double[] erro5 = new double[500];
	double[] erro6 = new double[500];
	double[][] errow = new double[6][1];
	double[][] errov = new double[6][1];
	
	//Funções de ativação e taxa de aprendizagem
	double alpha = 0.01;
	
	double[][] zw = new double[6][1];
	double[][] zwf = new double[6][1];
	double[][] zwff = new double[6][1];
	
	double[][] zv = new double[6][1];
	double[][] zvf = new double[6][1];
	double[][] zvff = new double[6][1];
	
	//Ajustes das sinapses W
	double[][] deltaw1 = new double[6][1];
	double[][] deltaw2 = new double[6][1];
	double[][] deltaw3 = new double[6][1];
	double[][] deltaw4 = new double[6][1];
	double[][] deltaw5 = new double[6][1];
	double[][] deltaw6 = new double[6][1];
	double [][] deltaw0 = {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}};
	double [][] deltay0 = {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}};	
	
	//Ajustes das sinapses V
	double[][] deltav1 = new double[6][1];
	double[][] deltav2 = new double[6][1];
	double[][] deltav3 = new double[6][1];
	double[][] deltav4 = new double[6][1];
	double[][] deltav5 = new double[6][1];
	double[][] deltav6 = new double[6][1];
	double[][] deltav0 = new double[6][1];
	
	//Ajustes das sinapses H
	double[][] deltah1 = new double[6][1];
	double[][] deltah2 = new double[6][1];
	double[][] deltah3 = new double[6][1];
	
	//Derivada das funções de ativação
	double[][] zz = new double[6][1];
	double[][] zzf = new double[6][1];
	double[][] zzff = new double[6][1];
	double[] yy = new double[300];
	double[] yyf = new double[300];
	double[] Yamanaka = new double[600];
	
	//Treino da rede
	public void iteracao_IV(double [] r0, double [] g0, double [] b0) {
		this.r = r0;
		this.g = g0;
		this.b = b0;
		int n = 0;
		
		//nº de trinamentos
		while(n < 80) {
			
			for(int x = 0; x < r.length; x++) {
				zv[0][0] = v0[0][0] + r[x] * h1[0][0] + g[x] * h2[0][0] + b[x] * h3[0][0];
				zv[1][0] = v0[1][0] + r[x] * h1[1][0] + g[x] * h2[1][0] + b[x] * h3[1][0];
				zv[2][0] = v0[2][0] + r[x] * h1[2][0] + g[x] * h2[2][0] + b[x] * h3[2][0];
				zv[3][0] = v0[3][0] + r[x] * h1[3][0] + g[x] * h2[3][0] + b[x] * h3[3][0];
				zv[4][0] = v0[4][0] + r[x] * h1[4][0] + g[x] * h2[4][0] + b[x] * h3[4][0];
				zv[5][0] = v0[5][0] + r[x] * h1[5][0] + g[x] * h2[5][0] + b[x] * h3[5][0];
				
				zvf[0][0] = 2 / (1 + (Math.exp(-zv[0][0]))) - 1;
				zvf[1][0] = 2 / (1 + (Math.exp(-zv[1][0]))) - 1;
				zvf[2][0] = 2 / (1 + (Math.exp(-zv[2][0]))) - 1;
				zvf[3][0] = 2 / (1 + (Math.exp(-zv[3][0]))) - 1;
				zvf[4][0] = 2 / (1 + (Math.exp(-zv[4][0]))) - 1;
				zvf[5][0] = 2 / (1 + (Math.exp(-zv[5][0]))) - 1;
				
				zvff[0][0] = 0.5 * (1 + zvf[0][0]) * (1 - zvf[0][0]);
				zvff[1][0] = 0.5 * (1 + zvf[1][0]) * (1 - zvf[1][0]);
				zvff[2][0] = 0.5 * (1 + zvf[2][0]) * (1 - zvf[2][0]);
				zvff[3][0] = 0.5 * (1 + zvf[3][0]) * (1 - zvf[3][0]);
				zvff[4][0] = 0.5 * (1 + zvf[4][0]) * (1 - zvf[4][0]);
				zvff[5][0] = 0.5 * (1 + zvf[5][0]) * (1 - zvf[5][0]);
				
				zw[0][0] = w0[0][0] + zvf[0][0] * v1[0][0] + zvf[1][0] * v2[0][0] + zvf[2][0] * v3[0][0] + zvf[3][0] * v4[0][0] + zvf[4][0] * v5[0][0] + zvf[5][0] * v6[0][0];
				zw[1][0] = w0[1][0] + zvf[0][0] * v1[1][0] + zvf[1][0] * v2[1][0] + zvf[2][0] * v3[1][0] + zvf[3][0] * v4[1][0] + zvf[4][0] * v5[1][0] + zvf[5][0] * v6[1][0];
				zw[2][0] = w0[2][0] + zvf[0][0] * v1[2][0] + zvf[1][0] * v2[2][0] + zvf[2][0] * v3[2][0] + zvf[3][0] * v4[2][0] + zvf[4][0] * v5[2][0] + zvf[5][0] * v6[2][0];
				zw[3][0] = w0[3][0] + zvf[0][0] * v1[3][0] + zvf[1][0] * v2[3][0] + zvf[2][0] * v3[3][0] + zvf[3][0] * v4[3][0] + zvf[4][0] * v5[3][0] + zvf[5][0] * v6[3][0];
				zw[4][0] = w0[4][0] + zvf[0][0] * v1[4][0] + zvf[1][0] * v2[4][0] + zvf[2][0] * v3[4][0] + zvf[3][0] * v4[4][0] + zvf[4][0] * v5[4][0] + zvf[5][0] * v6[4][0];
				zw[5][0] = w0[5][0] + zvf[0][0] * v1[5][0] + zvf[1][0] * v2[5][0] + zvf[2][0] * v3[5][0] + zvf[3][0] * v4[5][0] + zvf[4][0] * v5[5][0] + zvf[5][0] * v6[5][0];
				
				
				
				zwf[0][0] = 2 / (1 + (Math.exp(-zw[0][0]))) - 1;
				zwf[1][0] = 2 / (1 + (Math.exp(-zw[1][0]))) - 1;
				zwf[2][0] = 2 / (1 + (Math.exp(-zw[2][0]))) - 1;
				zwf[3][0] = 2 / (1 + (Math.exp(-zw[3][0]))) - 1;
				zwf[4][0] = 2 / (1 + (Math.exp(-zw[4][0]))) - 1;
				zwf[5][0] = 2 / (1 + (Math.exp(-zw[5][0]))) - 1;
				
				zwff[0][0] = 0.5 * (1 + zwf[0][0]) * (1 - zwf[0][0]);
				zwff[1][0] = 0.5 * (1 + zwf[1][0]) * (1 - zwf[1][0]);
				zwff[2][0] = 0.5 * (1 + zwf[2][0]) * (1 - zwf[2][0]);
				zwff[3][0] = 0.5 * (1 + zwf[3][0]) * (1 - zwf[3][0]);
				zwff[4][0] = 0.5 * (1 + zwf[4][0]) * (1 - zwf[4][0]);
				zwff[5][0] = 0.5 * (1 + zwf[5][0]) * (1 - zwf[5][0]);
				
				y1[x] = y0[0][0] + zwf[0][0] * w1[0][0] + zwf[1][0] * w2[0][0] + zwf[2][0] * w3[0][0] + zwf[3][0] * w4[0][0] + zwf[4][0] * w5[0][0] + zwf[5][0] * w6[0][0];
				y2[x] = y0[1][0] + zwf[0][0] * w1[1][0] + zwf[1][0] * w2[1][0] + zwf[2][0] * w3[1][0] + zwf[3][0] * w4[1][0] + zwf[4][0] * w5[1][0] + zwf[5][0] * w6[1][0];
				y3[x] = y0[2][0] + zwf[0][0] * w1[2][0] + zwf[1][0] * w2[2][0] + zwf[2][0] * w3[2][0] + zwf[3][0] * w4[2][0] + zwf[4][0] * w5[2][0] + zwf[5][0] * w6[2][0];
				y4[x] = y0[3][0] + zwf[0][0] * w1[3][0] + zwf[1][0] * w2[3][0] + zwf[2][0] * w3[3][0] + zwf[3][0] * w4[3][0] + zwf[4][0] * w5[3][0] + zwf[5][0] * w6[3][0];
				y5[x] = y0[4][0] + zwf[0][0] * w1[4][0] + zwf[1][0] * w2[4][0] + zwf[2][0] * w3[4][0] + zwf[3][0] * w4[4][0] + zwf[4][0] * w5[4][0] + zwf[5][0] * w6[4][0];
				y6[x] = y0[5][0] + zwf[0][0] * w1[5][0] + zwf[1][0] * w2[5][0] + zwf[2][0] * w3[5][0] + zwf[3][0] * w4[5][0] + zwf[4][0] * w5[5][0] + zwf[5][0] * w6[5][0];
				
				
				
				y1f[x] = 2 / (1 + (Math.exp(-y1[x]))) - 1;
				erro1[x] = (r[x] - y1f[x]);
				y1ff[x] = 0.5 * (1 + y1f[x]) * (1 - y1f[x]);
				errow[0][0] = alpha * erro1[x] * y1ff[x];
				
				y2f[x] = 2 / (1 + (Math.exp(-y2[x]))) - 1;
				erro2[x] = (g[x] - y2f[x]);
				y2ff[x] = 0.5 * (1 + y2f[x]) * (1 - y2f[x]);
				errow[1][0] = alpha * erro2[x] * y2ff[x];

				y3f[x] = 2 / (1 + (Math.exp(-y3[x]))) - 1;
				erro3[x] = (b[x] - y3f[x]);
				y3ff[x] = 0.5 * (1 + y3f[x]) * (1 - y3f[x]);
				errow[2][0] = alpha * erro3[x] * y3ff[x];
				
				y4f[x] = 2 / (1 + (Math.exp(-y4[x]))) - 1;
				erro4[x] = (r[x] - y4f[x]);
				y4ff[x] = 0.5 * (1 + y4f[x]) * (1 - y4f[x]);
				errow[3][0] = alpha * erro4[x] * y4ff[x];
				
				y5f[x] = 2 / (1 + (Math.exp(-y5[x]))) - 1;
				erro5[x] = (g[x] - y5f[x]);
				y5ff[x] = 0.5 * (1 + y5f[x]) * (1 - y5f[x]);
				errow[4][0] = alpha * erro5[x] * y5ff[x];
				
				y6f[x] = 2 / (1 + (Math.exp(-y6[x]))) - 1;
				erro1[x] = (b[x] - y6f[x]);
				y6ff[x] = 0.5 * (1 + y6f[x]) * (1 - y6f[x]);
				errow[5][0] = alpha * erro6[x] * y6ff[x];
			}
			
			for(int x = 0; x < r.length; x++) {
				deltay0[0][0] = alpha * erro1[x];
				deltay0[1][0] = alpha * erro2[x];
				deltay0[2][0] = alpha * erro3[x];
				deltay0[3][0] = alpha * erro4[x];
				deltay0[4][0] = alpha * erro5[x];
				deltay0[5][0] = alpha * erro6[x];
				
				y0[0][0] = y0[0][0] + deltay0[0][0];
				y0[1][0] = y0[1][0] + deltay0[1][0];
				y0[2][0] = y0[2][0] + deltay0[2][0];
				y0[3][0] = y0[3][0] + deltay0[3][0];
				y0[4][0] = y0[4][0] + deltay0[4][0];
				y0[5][0] = y0[5][0] + deltay0[5][0];
			}
			
			for(int x = 0; x < r.length; x++) {
				deltaw1[0][0] = errow[0][0] * zwf[0][0];
				deltaw1[1][0] = errow[0][0] * zwf[1][0];
				deltaw1[2][0] = errow[0][0] * zwf[2][0];
				deltaw1[3][0] = errow[0][0] * zwf[3][0];
				deltaw1[4][0] = errow[0][0] * zwf[4][0];
				deltaw1[5][0] = errow[0][0] * zwf[5][0];
				
				deltaw2[0][0] = errow[1][0] * zwf[0][0];
				deltaw2[1][0] = errow[1][0] * zwf[1][0];
				deltaw2[2][0] = errow[1][0] * zwf[2][0];
				deltaw2[3][0] = errow[1][0] * zwf[3][0];
				deltaw2[4][0] = errow[1][0] * zwf[4][0];
				deltaw2[5][0] = errow[1][0] * zwf[5][0];

				deltaw3[0][0] = errow[2][0] * zwf[0][0];
				deltaw3[1][0] = errow[2][0] * zwf[1][0];
				deltaw3[2][0] = errow[2][0] * zwf[2][0];
				deltaw3[3][0] = errow[2][0] * zwf[3][0];
				deltaw3[4][0] = errow[2][0] * zwf[4][0];
				deltaw3[5][0] = errow[2][0] * zwf[5][0];

				deltaw4[0][0] = errow[3][0] * zwf[0][0];
				deltaw4[1][0] = errow[3][0] * zwf[1][0];
				deltaw4[2][0] = errow[3][0] * zwf[2][0];
				deltaw4[3][0] = errow[3][0] * zwf[3][0];
				deltaw4[4][0] = errow[3][0] * zwf[4][0];
				deltaw4[5][0] = errow[3][0] * zwf[5][0];
				
				deltaw5[0][0] = errow[4][0] * zwf[0][0];
				deltaw5[1][0] = errow[4][0] * zwf[1][0];
				deltaw5[2][0] = errow[4][0] * zwf[2][0];
				deltaw5[3][0] = errow[4][0] * zwf[3][0];
				deltaw5[4][0] = errow[4][0] * zwf[4][0];
				deltaw5[5][0] = errow[4][0] * zwf[5][0];
				
				deltaw6[0][0] = errow[5][0] * zwf[0][0];
				deltaw6[1][0] = errow[5][0] * zwf[1][0];
				deltaw6[2][0] = errow[5][0] * zwf[2][0];
				deltaw6[3][0] = errow[5][0] * zwf[3][0];
				deltaw6[4][0] = errow[5][0] * zwf[4][0];
				deltaw6[5][0] = errow[5][0] * zwf[5][0];

				deltaw0[0][0] = alpha * erro1[x] * y1f[x];
				deltaw0[1][0] = alpha * erro2[x] * y2f[x];
				deltaw0[2][0] = alpha * erro3[x] * y3f[x];
				deltaw0[3][0] = alpha * erro4[x] * y4f[x];
				deltaw0[4][0] = alpha * erro5[x] * y5f[x];
				deltaw0[5][0] = alpha * erro6[x] * y6f[x];
				
				w1[0][0] = w1[0][0] + deltaw1[0][0];
				w1[1][0] = w1[1][0] + deltaw1[1][0];
				w1[2][0] = w1[2][0] + deltaw1[2][0];
				w1[3][0] = w1[3][0] + deltaw1[3][0];
				w1[4][0] = w1[4][0] + deltaw1[4][0];
				w1[5][0] = w1[5][0] + deltaw1[5][0];
				
				w2[0][0] = w2[0][0] + deltaw2[0][0];
				w2[1][0] = w2[1][0] + deltaw2[1][0];
				w2[2][0] = w2[2][0] + deltaw2[2][0];
				w2[3][0] = w2[3][0] + deltaw2[3][0];
				w2[4][0] = w2[4][0] + deltaw2[4][0];
				w2[5][0] = w2[5][0] + deltaw2[5][0];
				
				w3[0][0] = w3[0][0] + deltaw3[0][0];
				w3[1][0] = w3[1][0] + deltaw3[1][0];
				w3[2][0] = w3[2][0] + deltaw3[2][0];
				w3[3][0] = w3[3][0] + deltaw3[3][0];
				w3[4][0] = w3[4][0] + deltaw3[4][0];
				w3[5][0] = w3[5][0] + deltaw3[5][0];
				
				w4[0][0] = w4[0][0] + deltaw4[0][0];
				w4[1][0] = w4[1][0] + deltaw4[1][0];
				w4[2][0] = w4[2][0] + deltaw4[2][0];
				w4[3][0] = w4[3][0] + deltaw4[3][0];
				w4[4][0] = w4[4][0] + deltaw4[4][0];
				w4[5][0] = w4[5][0] + deltaw4[5][0];
				
				w5[0][0] = w5[0][0] + deltaw5[0][0];
				w5[1][0] = w5[1][0] + deltaw5[1][0];
				w5[2][0] = w5[2][0] + deltaw5[2][0];
				w5[3][0] = w5[3][0] + deltaw5[3][0];
				w5[4][0] = w5[4][0] + deltaw5[4][0];
				w5[5][0] = w5[5][0] + deltaw5[5][0];
				
				w6[0][0] = w6[0][0] + deltaw6[0][0];
				w6[1][0] = w6[1][0] + deltaw6[1][0];
				w6[2][0] = w6[2][0] + deltaw6[2][0];
				w6[3][0] = w6[3][0] + deltaw6[3][0];
				w6[4][0] = w6[4][0] + deltaw6[4][0];
				w6[5][0] = w6[5][0] + deltaw6[5][0];
				
				w0[0][0] = w0[0][0] + deltaw0[0][0];
				w0[1][0] = w0[1][0] + deltaw0[1][0];
				w0[2][0] = w0[2][0] + deltaw0[2][0];
				w0[3][0] = w0[3][0] + deltaw0[3][0];
				w0[4][0] = w0[4][0] + deltaw0[4][0];
				w0[5][0] = w0[5][0] + deltaw0[5][0];
			}
			
			for(int x = 0; x <r.length; x++) {
				deltav1[0][0] = alpha * zvff[0][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[0][0];
				deltav1[1][0] = alpha * zvff[0][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[0][0];
				deltav1[2][0] = alpha * zvff[0][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[0][0];
				deltav1[3][0] = alpha * zvff[0][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[0][0];
				deltav1[4][0] = alpha * zvff[0][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[0][0];
				deltav1[5][0] = alpha * zvff[0][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[0][0];
				
				deltav2[0][0] = alpha * zvff[1][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[1][0];
				deltav2[1][0] = alpha * zvff[1][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[1][0];
				deltav2[2][0] = alpha * zvff[1][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[1][0];
				deltav2[3][0] = alpha * zvff[1][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[1][0];
				deltav2[4][0] = alpha * zvff[1][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[1][0];
				deltav2[5][0] = alpha * zvff[1][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[1][0];

				deltav3[0][0] = alpha * zvff[2][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[2][0];
				deltav3[1][0] = alpha * zvff[2][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[2][0];
				deltav3[2][0] = alpha * zvff[2][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[2][0];
				deltav3[3][0] = alpha * zvff[2][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[2][0];
				deltav3[4][0] = alpha * zvff[2][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[2][0];
				deltav3[5][0] = alpha * zvff[2][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[2][0];
				
				deltav4[0][0] = alpha * zvff[3][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[3][0];
				deltav4[1][0] = alpha * zvff[3][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[3][0];
				deltav4[2][0] = alpha * zvff[3][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[3][0];
				deltav4[3][0] = alpha * zvff[3][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[3][0];
				deltav4[4][0] = alpha * zvff[3][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[3][0];
				deltav4[5][0] = alpha * zvff[3][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[3][0];
				
				deltav5[0][0] = alpha * zvff[4][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[4][0];
				deltav5[1][0] = alpha * zvff[4][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[4][0];
				deltav5[2][0] = alpha * zvff[4][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[4][0];
				deltav5[3][0] = alpha * zvff[4][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[4][0];
				deltav5[4][0] = alpha * zvff[4][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[4][0];
				deltav5[5][0] = alpha * zvff[4][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[4][0];
				
				deltav6[0][0] = alpha * zvff[5][0] * zwff[0][0] * (erro1[x] * w1[0][0] + erro2[x] * w1[1][0] + erro3[x] * w1[2][0] + erro4[x] * w1[3][0] + erro5[x] * w1[4][0] + erro6[x] * w1[5][0]) * zvf[5][0];
				deltav6[1][0] = alpha * zvff[5][0] * zwff[1][0] * (erro1[x] * w2[0][0] + erro2[x] * w2[1][0] + erro3[x] * w2[2][0] + erro4[x] * w2[3][0] + erro5[x] * w2[4][0] + erro6[x] * w2[5][0]) * zvf[5][0];
				deltav6[2][0] = alpha * zvff[5][0] * zwff[2][0] * (erro1[x] * w3[0][0] + erro2[x] * w3[1][0] + erro3[x] * w3[2][0] + erro4[x] * w3[3][0] + erro5[x] * w3[4][0] + erro6[x] * w3[5][0]) * zvf[5][0];
				deltav6[3][0] = alpha * zvff[5][0] * zwff[3][0] * (erro1[x] * w4[0][0] + erro2[x] * w4[1][0] + erro3[x] * w4[2][0] + erro4[x] * w4[3][0] + erro5[x] * w4[4][0] + erro6[x] * w4[5][0]) * zvf[5][0];
				deltav6[4][0] = alpha * zvff[5][0] * zwff[4][0] * (erro1[x] * w5[0][0] + erro2[x] * w5[1][0] + erro3[x] * w5[2][0] + erro4[x] * w5[3][0] + erro5[x] * w5[4][0] + erro6[x] * w5[5][0]) * zvf[5][0];
				deltav6[5][0] = alpha * zvff[5][0] * zwff[5][0] * (erro1[x] * w6[0][0] + erro2[x] * w6[1][0] + erro3[x] * w6[2][0] + erro4[x] * w6[3][0] + erro5[x] * w6[4][0] + erro6[x] * w6[5][0]) * zvf[5][0];
				
				deltav0[0][0] = alpha * erro1[x] * zwf[0][0];
				deltav0[1][0] = alpha * erro2[x] * zwf[1][0];
				deltav0[2][0] = alpha * erro3[x] * zwf[2][0];
				deltav0[3][0] = alpha * erro4[x] * zwf[3][0];
				deltav0[4][0] = alpha * erro5[x] * zwf[4][0];
				deltav0[5][0] = alpha * erro6[x] * zwf[5][0];
				
				v1[0][0] = v1[0][0] + deltav1[0][0];
				v1[1][0] = v1[1][0] + deltav1[1][0];
				v1[2][0] = v1[2][0] + deltav1[2][0];
				v1[3][0] = v1[3][0] + deltav1[3][0];
				v1[4][0] = v1[4][0] + deltav1[4][0];
				v1[5][0] = v1[5][0] + deltav1[5][0];
				
				v2[0][0] = v2[0][0] + deltav2[0][0];
				v2[1][0] = v2[1][0] + deltav2[1][0];
				v2[2][0] = v2[2][0] + deltav2[2][0];
				v2[3][0] = v2[3][0] + deltav2[3][0];
				v2[4][0] = v2[4][0] + deltav2[4][0];
				v2[5][0] = v2[5][0] + deltav2[5][0];
				
				v3[0][0] = v3[0][0] + deltav3[0][0];
				v3[1][0] = v3[1][0] + deltav3[1][0];
				v3[2][0] = v3[2][0] + deltav3[2][0];
				v3[3][0] = v3[3][0] + deltav3[3][0];
				v3[4][0] = v3[4][0] + deltav3[4][0];
				v3[5][0] = v3[5][0] + deltav3[5][0];
				
				v4[0][0] = v4[0][0] + deltav4[0][0];
				v4[1][0] = v4[1][0] + deltav4[1][0];
				v4[2][0] = v4[2][0] + deltav4[2][0];
				v4[3][0] = v4[3][0] + deltav4[3][0];
				v4[4][0] = v4[4][0] + deltav4[4][0];
				v4[5][0] = v4[5][0] + deltav4[5][0];
				
				v5[0][0] = v5[0][0] + deltav5[0][0];
				v5[1][0] = v5[1][0] + deltav5[1][0];
				v5[2][0] = v5[2][0] + deltav5[2][0];
				v5[3][0] = v5[3][0] + deltav5[3][0];
				v5[4][0] = v5[4][0] + deltav5[4][0];
				v5[5][0] = v5[5][0] + deltav5[5][0];
				
				v6[0][0] = v6[0][0] + deltav6[0][0];
				v6[1][0] = v6[1][0] + deltav6[1][0];
				v6[2][0] = v6[2][0] + deltav6[2][0];
				v6[3][0] = v6[3][0] + deltav6[3][0];
				v6[4][0] = v6[4][0] + deltav6[4][0];
				v6[5][0] = v6[5][0] + deltav6[5][0];
				
				v0[0][0] = v0[0][0] + deltav0[0][0];
				v0[1][0] = v0[1][0] + deltav0[1][0];
				v0[2][0] = v0[2][0] + deltav0[2][0];
				v0[3][0] = v0[3][0] + deltav0[3][0];
				v0[4][0] = v0[4][0] + deltav0[4][0];
				v0[5][0] = v0[5][0] + deltav0[5][0];
			}
			
			for(int x = 0; x < r.length; x++) {
				deltah1[0][0] = deltav1[0][0] * v1[0][0] * zvff[0][0] * r[x] + deltav1[1][0] * v1[1][0] * zvff[0][0] * r[x] + deltav1[2][0] * v1[2][0] * zvff[0][0] * r[x] + deltav1[3][0] * v1[3][0] * zvff[0][0] * r[x] + deltav1[4][0] * v1[4][0] * zvff[0][0] * r[x] + deltav1[5][0] * v1[5][0] * zvff[0][0] * r[x];
				deltah1[1][0] = deltav2[0][0] * v2[0][0] * zvff[1][0] * r[x] + deltav2[1][0] * v2[1][0] * zvff[1][0] * r[x] + deltav2[2][0] * v2[2][0] * zvff[1][0] * r[x] + deltav2[3][0] * v2[3][0] * zvff[1][0] * r[x] + deltav2[4][0] * v2[4][0] * zvff[1][0] * r[x] + deltav2[5][0] * v2[5][0] * zvff[1][0] * r[x];
				deltah1[2][0] = deltav3[0][0] * v3[0][0] * zvff[2][0] * r[x] + deltav3[1][0] * v3[1][0] * zvff[2][0] * r[x] + deltav3[2][0] * v3[2][0] * zvff[2][0] * r[x] + deltav3[3][0] * v3[3][0] * zvff[2][0] * r[x] + deltav3[4][0] * v3[4][0] * zvff[2][0] * r[x] + deltav3[5][0] * v3[5][0] * zvff[2][0] * r[x];
				deltah1[3][0] = deltav4[0][0] * v4[0][0] * zvff[3][0] * r[x] + deltav4[1][0] * v4[1][0] * zvff[3][0] * r[x] + deltav4[2][0] * v4[2][0] * zvff[3][0] * r[x] + deltav4[3][0] * v4[3][0] * zvff[3][0] * r[x] + deltav4[4][0] * v4[4][0] * zvff[3][0] * r[x] + deltav4[5][0] * v4[5][0] * zvff[3][0] * r[x];
				deltah1[4][0] = deltav5[0][0] * v5[0][0] * zvff[4][0] * r[x] + deltav5[1][0] * v5[1][0] * zvff[4][0] * r[x] + deltav5[2][0] * v5[2][0] * zvff[4][0] * r[x] + deltav5[3][0] * v5[3][0] * zvff[4][0] * r[x] + deltav5[4][0] * v5[4][0] * zvff[4][0] * r[x] + deltav5[5][0] * v5[5][0] * zvff[4][0] * r[x];
				deltah1[5][0] = deltav6[0][0] * v6[0][0] * zvff[5][0] * r[x] + deltav6[1][0] * v6[1][0] * zvff[5][0] * r[x] + deltav6[2][0] * v6[2][0] * zvff[5][0] * r[x] + deltav6[3][0] * v6[3][0] * zvff[5][0] * r[x] + deltav6[4][0] * v6[4][0] * zvff[5][0] * r[x] + deltav6[5][0] * v6[5][0] * zvff[5][0] * r[x];
				
				deltah2[0][0] = deltav1[0][0] * v1[0][0] * zvff[0][0] * r[x] + deltav1[1][0] * v1[1][0] * zvff[0][0] * r[x] + deltav1[2][0] * v1[2][0] * zvff[0][0] * r[x] + deltav1[3][0] * v1[3][0] * zvff[0][0] * r[x] + deltav1[4][0] * v1[4][0] * zvff[0][0] * r[x] + deltav1[5][0] * v1[5][0] * zvff[0][0] * r[x];
				deltah2[1][0] = deltav2[0][0] * v2[0][0] * zvff[1][0] * r[x] + deltav2[1][0] * v2[1][0] * zvff[1][0] * r[x] + deltav2[2][0] * v2[2][0] * zvff[1][0] * r[x] + deltav2[3][0] * v2[3][0] * zvff[1][0] * r[x] + deltav2[4][0] * v2[4][0] * zvff[1][0] * r[x] + deltav2[5][0] * v2[5][0] * zvff[1][0] * r[x];
				deltah2[2][0] = deltav3[0][0] * v3[0][0] * zvff[2][0] * r[x] + deltav3[1][0] * v3[1][0] * zvff[2][0] * r[x] + deltav3[2][0] * v3[2][0] * zvff[2][0] * r[x] + deltav3[3][0] * v3[3][0] * zvff[2][0] * r[x] + deltav3[4][0] * v3[4][0] * zvff[2][0] * r[x] + deltav3[5][0] * v3[5][0] * zvff[2][0] * r[x];
				deltah2[3][0] = deltav4[0][0] * v4[0][0] * zvff[3][0] * r[x] + deltav4[1][0] * v4[1][0] * zvff[3][0] * r[x] + deltav4[2][0] * v4[2][0] * zvff[3][0] * r[x] + deltav4[3][0] * v4[3][0] * zvff[3][0] * r[x] + deltav4[4][0] * v4[4][0] * zvff[3][0] * r[x] + deltav4[5][0] * v4[5][0] * zvff[3][0] * r[x];
				deltah2[4][0] = deltav5[0][0] * v5[0][0] * zvff[4][0] * r[x] + deltav5[1][0] * v5[1][0] * zvff[4][0] * r[x] + deltav5[2][0] * v5[2][0] * zvff[4][0] * r[x] + deltav5[3][0] * v5[3][0] * zvff[4][0] * r[x] + deltav5[4][0] * v5[4][0] * zvff[4][0] * r[x] + deltav5[5][0] * v5[5][0] * zvff[4][0] * r[x];
				deltah2[5][0] = deltav6[0][0] * v6[0][0] * zvff[5][0] * r[x] + deltav6[1][0] * v6[1][0] * zvff[5][0] * r[x] + deltav6[2][0] * v6[2][0] * zvff[5][0] * r[x] + deltav6[3][0] * v6[3][0] * zvff[5][0] * r[x] + deltav6[4][0] * v6[4][0] * zvff[5][0] * r[x] + deltav6[5][0] * v6[5][0] * zvff[5][0] * r[x];
				
				deltah3[0][0] = deltav1[0][0] * v1[0][0] * zvff[0][0] * r[x] + deltav1[1][0] * v1[1][0] * zvff[0][0] * r[x] + deltav1[2][0] * v1[2][0] * zvff[0][0] * r[x] + deltav1[3][0] * v1[3][0] * zvff[0][0] * r[x] + deltav1[4][0] * v1[4][0] * zvff[0][0] * r[x] + deltav1[5][0] * v1[5][0] * zvff[0][0] * r[x];
				deltah3[1][0] = deltav2[0][0] * v2[0][0] * zvff[1][0] * r[x] + deltav2[1][0] * v2[1][0] * zvff[1][0] * r[x] + deltav2[2][0] * v2[2][0] * zvff[1][0] * r[x] + deltav2[3][0] * v2[3][0] * zvff[1][0] * r[x] + deltav2[4][0] * v2[4][0] * zvff[1][0] * r[x] + deltav2[5][0] * v2[5][0] * zvff[1][0] * r[x];
				deltah3[2][0] = deltav3[0][0] * v3[0][0] * zvff[2][0] * r[x] + deltav3[1][0] * v3[1][0] * zvff[2][0] * r[x] + deltav3[2][0] * v3[2][0] * zvff[2][0] * r[x] + deltav3[3][0] * v3[3][0] * zvff[2][0] * r[x] + deltav3[4][0] * v3[4][0] * zvff[2][0] * r[x] + deltav3[5][0] * v3[5][0] * zvff[2][0] * r[x];
				deltah3[3][0] = deltav4[0][0] * v4[0][0] * zvff[3][0] * r[x] + deltav4[1][0] * v4[1][0] * zvff[3][0] * r[x] + deltav4[2][0] * v4[2][0] * zvff[3][0] * r[x] + deltav4[3][0] * v4[3][0] * zvff[3][0] * r[x] + deltav4[4][0] * v4[4][0] * zvff[3][0] * r[x] + deltav4[5][0] * v4[5][0] * zvff[3][0] * r[x];
				deltah3[4][0] = deltav5[0][0] * v5[0][0] * zvff[4][0] * r[x] + deltav5[1][0] * v5[1][0] * zvff[4][0] * r[x] + deltav5[2][0] * v5[2][0] * zvff[4][0] * r[x] + deltav5[3][0] * v5[3][0] * zvff[4][0] * r[x] + deltav5[4][0] * v5[4][0] * zvff[4][0] * r[x] + deltav5[5][0] * v5[5][0] * zvff[4][0] * r[x];
				deltah3[5][0] = deltav6[0][0] * v6[0][0] * zvff[5][0] * r[x] + deltav6[1][0] * v6[1][0] * zvff[5][0] * r[x] + deltav6[2][0] * v6[2][0] * zvff[5][0] * r[x] + deltav6[3][0] * v6[3][0] * zvff[5][0] * r[x] + deltav6[4][0] * v6[4][0] * zvff[5][0] * r[x] + deltav6[5][0] * v6[5][0] * zvff[5][0] * r[x];
				
				h1[0][0] = h1[0][0] + deltah1[0][0];
				h1[1][0] = h1[1][0] + deltah1[1][0];
				h1[2][0] = h1[2][0] + deltah1[2][0];
				h1[3][0] = h1[3][0] + deltah1[3][0];
				h1[4][0] = h1[4][0] + deltah1[4][0];
				h1[5][0] = h1[5][0] + deltah1[5][0];
				
				h2[0][0] = h2[0][0] + deltah2[0][0];
				h2[1][0] = h2[1][0] + deltah2[1][0];
				h2[2][0] = h2[2][0] + deltah2[2][0];
				h2[3][0] = h2[3][0] + deltah2[3][0];
				h2[4][0] = h2[4][0] + deltah2[4][0];
				h2[5][0] = h2[5][0] + deltah2[5][0];
				
				h3[0][0] = h3[0][0] + deltah3[0][0];
				h3[1][0] = h3[1][0] + deltah3[1][0];
				h3[2][0] = h3[2][0] + deltah3[2][0];
				h3[3][0] = h3[3][0] + deltah3[3][0];
				h3[4][0] = h3[4][0] + deltah3[4][0];
				h3[5][0] = h3[5][0] + deltah3[5][0];
			}
			n = n + 1;
			System.out.println("Numero de treinamento " + n + ".\n");
		}
		//Fim do treinamento
	}
	
	public void testaBackpropagationNCamadas(double [][]hh1, double [][]hh2, double [][]hh3, double [][]vv0, double [][]vv1, double [][]vv2, double [][]vv3, double [][]vv4, double [][]vv5, double [][]vv6, double [][]ww0, double [][]ww1, double [][]ww2, double [][]ww3, double [][]ww4, double [][]ww5, double [][]ww6, double [][]yy0, int f) {
		this.h1 = hh1;
		this.h2 = hh2;
		this.h3 = hh3;
		this.v0 = vv0;
		this.v1 = vv1;
		this.v2 = vv2;
		this.v3 = vv3;
		this.v4 = vv4;
		this.v5 = vv5;
		this.v6 = vv6;
		this.w0 = ww0;
		this.w1 = ww1;
		this.w2 = ww2;
		this.w3 = ww3;
		this.w4 = ww4;
		this.w5 = ww5;
		this.w6 = ww6;
		
		System.out.println("Pesos: \n");
		
		for(int j = 0; j <= 5; j++){
			System.out.println("Peso H1[" + j + "] = " + h1[j][0] + ".\n");
			System.out.println("Peso H2[" + j + "] = " + h2[j][0] + ".\n");
			System.out.println("Peso H3[" + j + "] = " + h3[j][0] + ".\n");
			
			System.out.println("Peso V0[" + j + "] = " + v0[j][0] + ".\n");
			System.out.println("Peso V1[" + j + "] = " + v1[j][0] + ".\n");
			System.out.println("Peso V2[" + j + "] = " + v2[j][0] + ".\n");
			System.out.println("Peso V3[" + j + "] = " + v3[j][0] + ".\n");
			System.out.println("Peso V4[" + j + "] = " + v4[j][0] + ".\n");
			System.out.println("Peso V5[" + j + "] = " + v5[j][0] + ".\n");
			System.out.println("Peso V6[" + j + "] = " + v6[j][0] + ".\n");
			
			System.out.println("Peso W0[" + j + "] = " + w0[j][0] + ".\n");
			System.out.println("Peso W1[" + j + "] = " + w1[j][0] + ".\n");
			System.out.println("Peso W2[" + j + "] = " + w2[j][0] + ".\n");
			System.out.println("Peso W3[" + j + "] = " + w3[j][0] + ".\n");
			System.out.println("Peso W4[" + j + "] = " + w4[j][0] + ".\n");
			System.out.println("Peso W5[" + j + "] = " + w5[j][0] + ".\n");
			System.out.println("Peso W6[" + j + "] = " + w6[j][0] + ".\n");
			
			System.out.println("Peso Y0[" + j + "] = " + y0[j][0] + ".\n");
		}
		
		for(int x = 0; x < r.length; x++) {
			zv[0][0] = v0[0][0] + r[x] * h1[0][0] + g[x] * h2[0][0] + b[x] * h3[0][0];
			zv[1][0] = v0[1][0] + r[x] * h1[1][0] + g[x] * h2[1][0] + b[x] * h3[1][0];
			zv[2][0] = v0[2][0] + r[x] * h1[2][0] + g[x] * h2[2][0] + b[x] * h3[2][0];
			zv[3][0] = v0[3][0] + r[x] * h1[3][0] + g[x] * h2[3][0] + b[x] * h3[3][0];
			zv[4][0] = v0[4][0] + r[x] * h1[4][0] + g[x] * h2[4][0] + b[x] * h3[4][0];
			zv[5][0] = v0[5][0] + r[x] * h1[5][0] + g[x] * h2[5][0] + b[x] * h3[5][0];
			
			zvf[0][0] = 2 / (1 + (Math.exp(-zv[0][0]))) - 1;
			zvf[1][0] = 2 / (1 + (Math.exp(-zv[1][0]))) - 1;
			zvf[2][0] = 2 / (1 + (Math.exp(-zv[2][0]))) - 1;
			zvf[3][0] = 2 / (1 + (Math.exp(-zv[3][0]))) - 1;
			zvf[4][0] = 2 / (1 + (Math.exp(-zv[4][0]))) - 1;
			zvf[5][0] = 2 / (1 + (Math.exp(-zv[5][0]))) - 1;
			
			zvff[0][0] = 0.5 * (1 + zvf[0][0]) * (1 - zvf[0][0]);
			zvff[1][0] = 0.5 * (1 + zvf[1][0]) * (1 - zvf[1][0]);
			zvff[2][0] = 0.5 * (1 + zvf[2][0]) * (1 - zvf[2][0]);
			zvff[3][0] = 0.5 * (1 + zvf[3][0]) * (1 - zvf[3][0]);
			zvff[4][0] = 0.5 * (1 + zvf[4][0]) * (1 - zvf[4][0]);
			zvff[5][0] = 0.5 * (1 + zvf[5][0]) * (1 - zvf[5][0]);
			
			zw[0][0] = w0[0][0] + zvf[0][0] * v1[0][0] + zvf[1][0] * v2[0][0] + zvf[2][0] * v3[0][0] + zvf[3][0] * v4[0][0] + zvf[4][0] * v5[0][0] + zvf[5][0] * v6[0][0];
			zw[1][0] = w0[1][0] + zvf[0][0] * v1[1][0] + zvf[1][0] * v2[1][0] + zvf[2][0] * v3[1][0] + zvf[3][0] * v4[1][0] + zvf[4][0] * v5[1][0] + zvf[5][0] * v6[1][0];
			zw[2][0] = w0[2][0] + zvf[0][0] * v1[2][0] + zvf[1][0] * v2[2][0] + zvf[2][0] * v3[2][0] + zvf[3][0] * v4[2][0] + zvf[4][0] * v5[2][0] + zvf[5][0] * v6[2][0];
			zw[3][0] = w0[3][0] + zvf[0][0] * v1[3][0] + zvf[1][0] * v2[3][0] + zvf[2][0] * v3[3][0] + zvf[3][0] * v4[3][0] + zvf[4][0] * v5[3][0] + zvf[5][0] * v6[3][0];
			zw[4][0] = w0[4][0] + zvf[0][0] * v1[4][0] + zvf[1][0] * v2[4][0] + zvf[2][0] * v3[4][0] + zvf[3][0] * v4[4][0] + zvf[4][0] * v5[4][0] + zvf[5][0] * v6[4][0];
			zw[5][0] = w0[5][0] + zvf[0][0] * v1[5][0] + zvf[1][0] * v2[5][0] + zvf[2][0] * v3[5][0] + zvf[3][0] * v4[5][0] + zvf[4][0] * v5[5][0] + zvf[5][0] * v6[5][0];
			
			
			
			zwf[0][0] = 2 / (1 + (Math.exp(-zw[0][0]))) - 1;
			zwf[1][0] = 2 / (1 + (Math.exp(-zw[1][0]))) - 1;
			zwf[2][0] = 2 / (1 + (Math.exp(-zw[2][0]))) - 1;
			zwf[3][0] = 2 / (1 + (Math.exp(-zw[3][0]))) - 1;
			zwf[4][0] = 2 / (1 + (Math.exp(-zw[4][0]))) - 1;
			zwf[5][0] = 2 / (1 + (Math.exp(-zw[5][0]))) - 1;
			
			zwff[0][0] = 0.5 * (1 + zwf[0][0]) * (1 - zwf[0][0]);
			zwff[1][0] = 0.5 * (1 + zwf[1][0]) * (1 - zwf[1][0]);
			zwff[2][0] = 0.5 * (1 + zwf[2][0]) * (1 - zwf[2][0]);
			zwff[3][0] = 0.5 * (1 + zwf[3][0]) * (1 - zwf[3][0]);
			zwff[4][0] = 0.5 * (1 + zwf[4][0]) * (1 - zwf[4][0]);
			zwff[5][0] = 0.5 * (1 + zwf[5][0]) * (1 - zwf[5][0]);
			
			y1[x] = y0[0][0] + zwf[0][0] * w1[0][0] + zwf[1][0] * w2[0][0] + zwf[2][0] * w3[0][0] + zwf[3][0] * w4[0][0] + zwf[4][0] * w5[0][0] + zwf[5][0] * w6[0][0];
			y2[x] = y0[1][0] + zwf[0][0] * w1[1][0] + zwf[1][0] * w2[1][0] + zwf[2][0] * w3[1][0] + zwf[3][0] * w4[1][0] + zwf[4][0] * w5[1][0] + zwf[5][0] * w6[1][0];
			y3[x] = y0[2][0] + zwf[0][0] * w1[2][0] + zwf[1][0] * w2[2][0] + zwf[2][0] * w3[2][0] + zwf[3][0] * w4[2][0] + zwf[4][0] * w5[2][0] + zwf[5][0] * w6[2][0];
			y4[x] = y0[3][0] + zwf[0][0] * w1[3][0] + zwf[1][0] * w2[3][0] + zwf[2][0] * w3[3][0] + zwf[3][0] * w4[3][0] + zwf[4][0] * w5[3][0] + zwf[5][0] * w6[3][0];
			y5[x] = y0[4][0] + zwf[0][0] * w1[4][0] + zwf[1][0] * w2[4][0] + zwf[2][0] * w3[4][0] + zwf[3][0] * w4[4][0] + zwf[4][0] * w5[4][0] + zwf[5][0] * w6[4][0];
			y6[x] = y0[5][0] + zwf[0][0] * w1[5][0] + zwf[1][0] * w2[5][0] + zwf[2][0] * w3[5][0] + zwf[3][0] * w4[5][0] + zwf[4][0] * w5[5][0] + zwf[5][0] * w6[5][0];
			
			
			
			y1f[x] = 2 / (1 + (Math.exp(-y1[x]))) - 1;
			y2f[x] = 2 / (1 + (Math.exp(-y2[x]))) - 1;
			y3f[x] = 2 / (1 + (Math.exp(-y3[x]))) - 1;
			y4f[x] = 2 / (1 + (Math.exp(-y4[x]))) - 1;
			y5f[x] = 2 / (1 + (Math.exp(-y5[x]))) - 1;
			y6f[x] = 2 / (1 + (Math.exp(-y6[x]))) - 1;
			
			System.out.println("FY1 [" + x + "] = " + y1f[x] + ".\n");
			System.out.println("FY2 [" + x + "] = " + y2f[x] + ".\n");
			System.out.println("FY3 [" + x + "] = " + y3f[x] + ".\n");
		}
		//Fim do teste		
	}
	
	public void generalizacao(double [][]hh1, double [][]hh2, double [][]hh3, double [][]vv0, double [][]vv1, double [][]vv2, double [][]vv3, double [][]vv4, double [][]vv5, double [][]vv6, double [][]ww0, double [][]ww1, double [][]ww2, double [][]ww3, double [][]ww4, double [][]ww5, double [][]ww6, double [][]yy0,  double []vermelho, double []verde, double []azul) {
		this.h1 = hh1;
		this.h2 = hh2;
		this.h3 = hh3;
		this.v0 = vv0;
		this.v1 = vv1;
		this.v2 = vv2;
		this.v3 = vv3;
		this.v4 = vv4;
		this.v5 = vv5;
		this.v6 = vv6;
		this.w0 = ww0;
		this.w1 = ww1;
		this.w2 = ww2;
		this.w3 = ww3;
		this.w4 = ww4;
		this.w5 = ww5;
		this.w6 = ww6;
		
		System.out.println("Pesos: \n");
		
		for(int j = 0; j <= 5; j++){
			System.out.println("Peso H1[" + j + "] = " + h1[j][0] + ".\n");
			System.out.println("Peso H2[" + j + "] = " + h2[j][0] + ".\n");
			System.out.println("Peso H3[" + j + "] = " + h3[j][0] + ".\n");
			
			System.out.println("Peso V0[" + j + "] = " + v0[j][0] + ".\n");
			System.out.println("Peso V1[" + j + "] = " + v1[j][0] + ".\n");
			System.out.println("Peso V2[" + j + "] = " + v2[j][0] + ".\n");
			System.out.println("Peso V3[" + j + "] = " + v3[j][0] + ".\n");
			System.out.println("Peso V4[" + j + "] = " + v4[j][0] + ".\n");
			System.out.println("Peso V5[" + j + "] = " + v5[j][0] + ".\n");
			System.out.println("Peso V6[" + j + "] = " + v6[j][0] + ".\n");
			
			System.out.println("Peso W0[" + j + "] = " + w0[j][0] + ".\n");
			System.out.println("Peso W1[" + j + "] = " + w1[j][0] + ".\n");
			System.out.println("Peso W2[" + j + "] = " + w2[j][0] + ".\n");
			System.out.println("Peso W3[" + j + "] = " + w3[j][0] + ".\n");
			System.out.println("Peso W4[" + j + "] = " + w4[j][0] + ".\n");
			System.out.println("Peso W5[" + j + "] = " + w5[j][0] + ".\n");
			System.out.println("Peso W6[" + j + "] = " + w6[j][0] + ".\n");
			
			System.out.println("Peso Y0[" + j + "] = " + y0[j][0] + ".\n");
		}
		
		for(int x = 0; x < r.length; x++) {
			zv[0][0] = v0[0][0] + vermelho[x] * h1[0][0] + verde[x] * h2[0][0] + azul[x] * h3[0][0];
			zv[1][0] = v0[1][0] + vermelho[x] * h1[1][0] + verde[x] * h2[1][0] + azul[x] * h3[1][0];
			zv[2][0] = v0[2][0] + vermelho[x] * h1[2][0] + verde[x] * h2[2][0] + azul[x] * h3[2][0];
			zv[3][0] = v0[3][0] + vermelho[x] * h1[3][0] + verde[x] * h2[3][0] + azul[x] * h3[3][0];
			zv[4][0] = v0[4][0] + vermelho[x] * h1[4][0] + verde[x] * h2[4][0] + azul[x] * h3[4][0];
			zv[5][0] = v0[5][0] + vermelho[x] * h1[5][0] + verde[x] * h2[5][0] + azul[x] * h3[5][0];
			
			zvf[0][0] = 2 / (1 + (Math.exp(-zv[0][0]))) - 1;
			zvf[1][0] = 2 / (1 + (Math.exp(-zv[1][0]))) - 1;
			zvf[2][0] = 2 / (1 + (Math.exp(-zv[2][0]))) - 1;
			zvf[3][0] = 2 / (1 + (Math.exp(-zv[3][0]))) - 1;
			zvf[4][0] = 2 / (1 + (Math.exp(-zv[4][0]))) - 1;
			zvf[5][0] = 2 / (1 + (Math.exp(-zv[5][0]))) - 1;
			
			zvff[0][0] = 0.5 * (1 + zvf[0][0]) * (1 - zvf[0][0]);
			zvff[1][0] = 0.5 * (1 + zvf[1][0]) * (1 - zvf[1][0]);
			zvff[2][0] = 0.5 * (1 + zvf[2][0]) * (1 - zvf[2][0]);
			zvff[3][0] = 0.5 * (1 + zvf[3][0]) * (1 - zvf[3][0]);
			zvff[4][0] = 0.5 * (1 + zvf[4][0]) * (1 - zvf[4][0]);
			zvff[5][0] = 0.5 * (1 + zvf[5][0]) * (1 - zvf[5][0]);
			
			zw[0][0] = w0[0][0] + zvf[0][0] * v1[0][0] + zvf[1][0] * v2[0][0] + zvf[2][0] * v3[0][0] + zvf[3][0] * v4[0][0] + zvf[4][0] * v5[0][0] + zvf[5][0] * v6[0][0];
			zw[1][0] = w0[1][0] + zvf[0][0] * v1[1][0] + zvf[1][0] * v2[1][0] + zvf[2][0] * v3[1][0] + zvf[3][0] * v4[1][0] + zvf[4][0] * v5[1][0] + zvf[5][0] * v6[1][0];
			zw[2][0] = w0[2][0] + zvf[0][0] * v1[2][0] + zvf[1][0] * v2[2][0] + zvf[2][0] * v3[2][0] + zvf[3][0] * v4[2][0] + zvf[4][0] * v5[2][0] + zvf[5][0] * v6[2][0];
			zw[3][0] = w0[3][0] + zvf[0][0] * v1[3][0] + zvf[1][0] * v2[3][0] + zvf[2][0] * v3[3][0] + zvf[3][0] * v4[3][0] + zvf[4][0] * v5[3][0] + zvf[5][0] * v6[3][0];
			zw[4][0] = w0[4][0] + zvf[0][0] * v1[4][0] + zvf[1][0] * v2[4][0] + zvf[2][0] * v3[4][0] + zvf[3][0] * v4[4][0] + zvf[4][0] * v5[4][0] + zvf[5][0] * v6[4][0];
			zw[5][0] = w0[5][0] + zvf[0][0] * v1[5][0] + zvf[1][0] * v2[5][0] + zvf[2][0] * v3[5][0] + zvf[3][0] * v4[5][0] + zvf[4][0] * v5[5][0] + zvf[5][0] * v6[5][0];
			
			
			
			zwf[0][0] = 2 / (1 + (Math.exp(-zw[0][0]))) - 1;
			zwf[1][0] = 2 / (1 + (Math.exp(-zw[1][0]))) - 1;
			zwf[2][0] = 2 / (1 + (Math.exp(-zw[2][0]))) - 1;
			zwf[3][0] = 2 / (1 + (Math.exp(-zw[3][0]))) - 1;
			zwf[4][0] = 2 / (1 + (Math.exp(-zw[4][0]))) - 1;
			zwf[5][0] = 2 / (1 + (Math.exp(-zw[5][0]))) - 1;
			
			zwff[0][0] = 0.5 * (1 + zwf[0][0]) * (1 - zwf[0][0]);
			zwff[1][0] = 0.5 * (1 + zwf[1][0]) * (1 - zwf[1][0]);
			zwff[2][0] = 0.5 * (1 + zwf[2][0]) * (1 - zwf[2][0]);
			zwff[3][0] = 0.5 * (1 + zwf[3][0]) * (1 - zwf[3][0]);
			zwff[4][0] = 0.5 * (1 + zwf[4][0]) * (1 - zwf[4][0]);
			zwff[5][0] = 0.5 * (1 + zwf[5][0]) * (1 - zwf[5][0]);
			
			y1[x] = y0[0][0] + zwf[0][0] * w1[0][0] + zwf[1][0] * w2[0][0] + zwf[2][0] * w3[0][0] + zwf[3][0] * w4[0][0] + zwf[4][0] * w5[0][0] + zwf[5][0] * w6[0][0];
			y2[x] = y0[1][0] + zwf[0][0] * w1[1][0] + zwf[1][0] * w2[1][0] + zwf[2][0] * w3[1][0] + zwf[3][0] * w4[1][0] + zwf[4][0] * w5[1][0] + zwf[5][0] * w6[1][0];
			y3[x] = y0[2][0] + zwf[0][0] * w1[2][0] + zwf[1][0] * w2[2][0] + zwf[2][0] * w3[2][0] + zwf[3][0] * w4[2][0] + zwf[4][0] * w5[2][0] + zwf[5][0] * w6[2][0];
			y4[x] = y0[3][0] + zwf[0][0] * w1[3][0] + zwf[1][0] * w2[3][0] + zwf[2][0] * w3[3][0] + zwf[3][0] * w4[3][0] + zwf[4][0] * w5[3][0] + zwf[5][0] * w6[3][0];
			y5[x] = y0[4][0] + zwf[0][0] * w1[4][0] + zwf[1][0] * w2[4][0] + zwf[2][0] * w3[4][0] + zwf[3][0] * w4[4][0] + zwf[4][0] * w5[4][0] + zwf[5][0] * w6[4][0];
			y6[x] = y0[5][0] + zwf[0][0] * w1[5][0] + zwf[1][0] * w2[5][0] + zwf[2][0] * w3[5][0] + zwf[3][0] * w4[5][0] + zwf[4][0] * w5[5][0] + zwf[5][0] * w6[5][0];
			
			
			
			y1f[x] = 2 / (1 + (Math.exp(-y1[x]))) - 1;
			y2f[x] = 2 / (1 + (Math.exp(-y2[x]))) - 1;
			y3f[x] = 2 / (1 + (Math.exp(-y3[x]))) - 1;
			y4f[x] = 2 / (1 + (Math.exp(-y4[x]))) - 1;
			y5f[x] = 2 / (1 + (Math.exp(-y5[x]))) - 1;
			y6f[x] = 2 / (1 + (Math.exp(-y6[x]))) - 1;
			
			System.out.println("FY1 [" + x + "] = " + y1f[x] + ".\n");
			System.out.println("FY2 [" + x + "] = " + y2f[x] + ".\n");
			System.out.println("FY3 [" + x + "] = " + y3f[x] + ".\n");
		}
		
	}	
}
