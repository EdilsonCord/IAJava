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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ObjetosGraficos extends JPanel {
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(imagem != null)
			g.drawImage(imagem, 0, 0, null);
	}
	
	public void loadImage(String name) {
		Image carregarImagem = Toolkit.getDefaultToolkit().getImage(name);
		MediaTracker tracker = new MediaTracker(this);
		tracker.addImage(carregarImagem, 0);
		try {
			tracker.waitForID(0);
		}catch (InterruptedException e) {}
		imagem = new BufferedImage(carregarImagem.getWidth(null), carregarImagem.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = imagem.createGraphics();
		g2.drawImage(carregarImagem, 0, 0, null);
		
		WritableRaster varredura = imagem.getRaster();
		varredura.getPixels(0, 0, 300, 300, amostragem);
		
		int p = 0,q = 0,s = 0;
		int []y = {0};
		
		for(int u = 0; u <= amostragem.length - 1; u = u + 3) {
			red[p] = amostragem[u];
			p++;
		}
		for(int u = 1; u <= amostragem.length - 1; u = u + 3) {
			green[q] = amostragem[u];
			q++;
		}
		for(int u = 2; u <= amostragem.length - 1; u = u + 3) {
			blue[s] = amostragem[u];
			s++;
		}
		
		repaint();
	}
	
	private void filtro(BufferedImageOp op) {
		BufferedImage imagemFiltrada = new BufferedImage(imagem.getWidth(), imagem.getHeight(), imagem.getType());
		op.filter(imagem, imagemFiltrada);
		imagem = imagemFiltrada;
		repaint();
	}
	
	public void encaminha() {
		gravaImagem(imagem);
	}
	
	private void convolucao(float[] elementos) {
		Kernel kernel = new Kernel(3, 3, elementos);
		ConvolveOp op = new ConvolveOp(kernel);
		filtro(op);
	}
	
	public void blur() {
		float weight = 1.0f/9.0f;
		float[] elementos = new float[9];
		for(int i=0; i < 9; i++) 
			elementos[i] = weight;
		convolucao(elementos);
	}
	
	public void intensifica() {
		float[] elementos = {
			0.0f, -1.0f, 0.0f,
			-1.0f, 5.f, -1.0f,
			0.0f, -1.0f, 0.0f
		};
		convolucao(elementos);
	}
	
	void borda() {
		float[] elementos = {
			0.0f, -1.0f, 0.0f,
			-1.0f, 4.f, -1.0f,
			0.0f, -1.0f, 0.0f
		};
		convolucao(elementos);
	}
	
	public void ilumina() {
		float a = 1.5f;
		float b = -20.0f;
		RescaleOp op = new RescaleOp(a, b, null);
		filtro(op);
	}
	
	void inverte() {
		byte negativo[] = new byte[256];
		for(int i = 0; i<256; i++)
			negativo[i] = (byte)(255 - i);
		ByteLookupTable table = new ByteLookupTable(0, negativo);
		LookupOp op = new LookupOp(table, null);
		filtro(op);
	}
	
	void rotaciona() {
		AffineTransform transforma = AffineTransform.getRotateInstance(Math.toRadians(5), imagem.getWidth() / 2, imagem.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transforma, AffineTransformOp.TYPE_BILINEAR);
		filtro(op);
	}
	
	void converte() {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		
		WritableRaster raster = imagem.getRaster();
		ColorModel model = imagem.getColorModel();
		int[] amostragem = new int [4];
		int[] k = new int [largura*altura];
		int i, j;
		Object data = raster.getDataElements(0, 0, amostragem);
		
		int [][][] p = new int [altura][largura][4];
		
		for(i=0; i<altura; i++)
			for(j=0; j<largura; j++) {
				p[i][j] = raster.getPixel(j, i, amostragem);
				System.out.println("[" + i + "]\t[" + j + "]\t" + p[i][j][0] + "\t" + p[i][j][1] + "\t" + p[i][j][2] + "\t" + p[i][j][3] + "\t" );
			}
	}
	
	String pi;
	int x, y, yPos, xCor, yCor;
	BufferedImage g;
	
	public void gravaImagem(BufferedImage image) {
		try {
			ImageIO.write(image, "JPG", new File("memoria.jpg"));
		}catch (Exception erro) {
			System.out.println("Erro na gravação!");
		}
	}
	
	public void apagaArquivo() {
		try {
			StringBuffer memoria = new StringBuffer();
			BufferedReader arqEntrada;
			arqEntrada = new BufferedReader(new FileReader("rgb.txt"));
			String linha = " ";
			while((linha = arqEntrada.readLine()) != null) {
				memoria.append(linha);
			}
			if(memoria.length() != 0) {
				memoria.delete(0, 1000000000);
				BufferedWriter saida1 = new BufferedWriter(new FileWriter("rgb.txt"));
				saida1.write(memoria.toString());
				saida1.flush();
				saida1.close();
			}else {
				System.out.println("Arquivo vazio!");
			}
		}catch (Exception erro) {
			System.out.println("Erro no delete do arquivo!");
		}
	}
	
	public void gravaArquivo() {
		try {
			BufferedWriter saida = new BufferedWriter(new FileWriter("rgb.txt", true));
			saida.write("\n" + buffer);
			saida.flush();
			saida.close();
		}catch(Exception erro) {
			System.out.println("Erro na gravação!");
		}
	}
	
	public void treinaRedeNeural() {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		
		WritableRaster raster = imagem.getRaster();
		ColorModel model = imagem.getColorModel();
		int[] amostragem = new int [3];
		
		int[] k = new int [largura*altura];
		int i, j, m = 0;
		Object data = raster.getDataElements(0, 0, amostragem);
		
		int[] h = new int [4];
		int [][][] p = new int [altura][largura][3];
		
		System.out.println("//red");
		System.out.println("double [] r= (");
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostragem);
				System.out.println((double)p[i][j][0]/255+",");
				rr[m] = (double)p[i][j][0]/255;
				m++;
			}
		m=0;
		System.out.println("};");
		System.out.println("\n\n");
		System.out.println("//green");
		System.out.println("double [] g= {");
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostragem);
				System.out.println((double)p[i][j][1]/255+",");
				gg[m] = (double)p[i][j][1]/255;
				m++;
			}
		m=0;
		System.out.println("};");
		System.out.println("\n\n");
		System.out.println("//blue");
		System.out.println("double [] g= {");
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostragem);
				System.out.println((double)p[i][j][2]/255+",");
				bb[m] = (double)p[i][j][2]/255;
				m++;
			}
		System.out.println("};");
		
		bn.iteracao_IV(rr,gg,bb);
	}
	
	public void testeRedeNeural() {
		bn.testaBackpropagationNCamadas(bn.h1,bn.h2,bn.h3,bn.v0,bn.v1,bn.v2,bn.v3,bn.v4,bn.v5,bn.v6,bn.w0,bn.w1,bn.w2,bn.w3,bn.w4,bn.w5,bn.w6,bn.y0,x);
	}
	
	public void geraImagemRedeNeural() {
		int largura = 300;
		int altura = 300;
		double[] arranjoCores = new double [3*altura*largura];
		
		imagemNeural = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = imagemNeural.getRaster();
		
		int tt = 0;
		for(int t = 0; t< arranjoCores.length; t = t+3) {
			arranjoCores[t] = bn.y1f[tt]*255;
			arranjoCores[t+1] = bn.y2f[tt]*255;
			arranjoCores[t+2] = bn.y3f[tt]*255;
			
			System.out.println("Arranjo cores [0] " + arranjoCores[t]);
			System.out.println("Arranjo cores [1] " + arranjoCores[t+1]);
			System.out.println("Arranjo cores [2] " + arranjoCores[t+2]);
			tt++;
		}
		raster.setPixels(0,0,largura,altura,arranjoCores);
		gravaImagem(imagemNeural);
	}
	
	public void testeGeneralizacao() {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		
		WritableRaster raster = imagem.getRaster();
		ColorModel model = imagem.getColorModel();
		int[] amostra = new int [3];
		int i, j, m = 0;
		Object data = raster.getDataElements(0, 0, amostra);
		
		int [][][] p = new int [altura][largura][3];
		
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostra);
				System.out.println((double)p[i][j][0]/255+",");
				rr[m] = (double)p[i][j][0]/255;
				m++;
			}
		m=0;
		
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostra);
				System.out.println((double)p[i][j][1]/255+",");
				gg[m] = (double)p[i][j][1]/255;
				m++;
			}
		m=0;
		for(i=0; i<altura; i++)
			for(j=0;j<largura;j++) {
				p[i][j] = raster.getPixel(j,i,amostragem);
				System.out.println((double)p[i][j][2]/255+",");
				bb[m] = (double)p[i][j][2]/255;
				m++;
			}
		
		bn.generalizacao(bn.h1, bn.h2, bn.h3, bn.v0, bn.v1, bn.v2, bn.v3, bn.v4, bn.v5, bn.v6, bn.w0, bn.w1, bn.w2, bn.w3, bn.w4, bn.w5, bn.w6, bn.y0, rr, gg, bb);
	}
	
	StringBuffer buffer = new StringBuffer();
	int[] red = new int[1000000];
	int[] green = new int[1000000];
	int[] blue = new int[1000000];
	
	int linha, coluna;
	int[] amostragem = new int[1000000];
	int[] amostra = new int[3];
	
	private BufferedImage imagem, imagemNeural;
	Backpropagation_Camadas bn = new Backpropagation_Camadas();
	double[] rr = new double[500];
	double[] gg = new double[500];
	double[] bb = new double[500];	
	
}
