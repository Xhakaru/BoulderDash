package tile;

import main.GamePanel;

public class Chunk {
	
	GamePanel gp;
	public int cbLeft, cbRight, cbUp, cbDown;  // ChunkBorders saved as Tiles
	public int sbLeft, sbRight, sbUp, sbDown;  // ScreenBorders Saved as Tiles
	
	public Chunk(GamePanel gp, int cbl, int cbr, int cbu, int cbd) {
		cbLeft = cbl;
		cbRight = cbr;
		cbUp = cbu;
		cbDown = cbd;
		
		this.gp = gp;
		
		cameraPerChunk();
	}
	
	public void cameraPerChunk() {
		int switchcase = 0;
		if(cbLeft == 0) {
			switchcase++;
		} 
		if(cbRight == gp.maxWorldCol) {
			switchcase = switchcase + 10;
		}
		if(cbUp == 0) {
			switchcase = switchcase + 100;
		}
		if(cbDown == gp.maxWorldRow - 1) {
			switchcase = switchcase + 1000;
		}
		
		switch(switchcase) {
			case(0):
				int middleX = Math.round( (cbLeft + cbRight) / 2 );
				int middleY = Math.round( (cbUp + cbDown) / 2 );
				sbLeft = middleX - Math.round( (gp.maxScreenCol / 2) );
				sbRight = middleX + Math.round( (gp.maxScreenCol / 2) );
				sbUp = middleY - Math.round( ((gp.maxScreenRow - 1) / 2) );
				sbDown = middleY + Math.round( ((gp.maxScreenRow - 1) / 2) );
				break;
				
			case(1):
				sbLeft = 0;
				sbRight = gp.maxScreenCol;
				int middleY1 = Math.round( (cbUp + cbDown) / 2 );
				sbUp = middleY1 - Math.round( ((gp.maxScreenRow - 1) / 2) );
				sbDown = middleY1 + Math.round( ((gp.maxScreenRow - 1) / 2) );
				break;
				
			case(10):
				sbRight = gp.maxWorldCol;
				sbLeft = gp.maxWorldCol - gp.maxScreenCol;
				int middleY10 = Math.round( (cbUp + cbDown) / 2 );
				sbUp = middleY10 - Math.round( ((gp.maxScreenRow - 1) / 2) );
				sbDown = middleY10 + Math.round( ((gp.maxScreenRow - 1) / 2) );
				break;
				
			case(11):
				sbLeft = 0;
				sbRight = gp.maxWorldCol;
				int middleY11 = Math.round( (cbUp + cbDown) / 2 );
				sbUp = middleY11 - Math.round( ((gp.maxScreenRow - 1) / 2) );
				sbDown = middleY11 + Math.round( ((gp.maxScreenRow - 1) / 2) );
				break;
				
			case(100):
				sbUp = 0;
				sbDown = (gp.maxScreenRow - 1);
				int middleX100 = Math.round( (cbLeft + cbRight) / 2 );
				sbLeft = middleX100 - Math.round( (gp.maxScreenCol / 2) );
				sbRight = middleX100 + Math.round( (gp.maxScreenCol / 2) );
				break;
				
			case(101):
				sbUp = 0;
				sbDown = (gp.maxScreenRow - 1);
				sbLeft = 0;
				sbRight = gp.maxScreenCol;
				break;
				
			case(110):
				sbUp = 0;
				sbDown = (gp.maxScreenRow - 1);
				sbRight = gp.maxWorldCol;
				sbLeft = gp.maxWorldCol - gp.maxScreenCol;
				break;
				
			case(111):
				sbUp = 0;
				sbDown = (gp.maxScreenRow - 1);
				sbLeft = 0;
				sbRight = gp.maxWorldCol;
				break;
				
			case(1000):
				sbDown = gp.maxWorldRow - 1;
				sbUp = gp.maxWorldRow - 1 - (gp.maxScreenRow - 1);
				int middleX1000 = Math.round( (cbLeft + cbRight) / 2 );
				sbLeft = middleX1000 - Math.round( (gp.maxScreenCol / 2) );
				sbRight = middleX1000 + Math.round( (gp.maxScreenCol / 2) );
				break;
				
			case(1001):
				sbDown = gp.maxWorldRow - 1;
				sbUp = gp.maxWorldRow - 1 - (gp.maxScreenRow - 1);
				sbLeft = 0;
				sbRight = gp.maxScreenCol;
				break;
				
			case(1010):
				sbDown = gp.maxWorldRow - 1;
				sbUp = gp.maxWorldRow - 1 - (gp.maxScreenRow - 1);
				sbRight = gp.maxWorldCol;
				sbLeft = gp.maxWorldCol - gp.maxScreenCol;
				break;
				
			case(1011):
				sbDown = gp.maxWorldRow - 1;
				sbUp = gp.maxWorldRow - 1 -(gp.maxScreenRow - 1);
				sbLeft = 0;
				sbRight = gp.maxWorldCol;
				break;
				
			case(1100):
				sbDown = gp.maxWorldRow - 1;
				sbUp = 0;
				int middleX1100 = Math.round( (cbLeft + cbRight) / 2 );
				sbLeft = middleX1100 - Math.round( (gp.maxScreenCol / 2) );
				sbRight = middleX1100 + Math.round( (gp.maxScreenCol / 2) );
				break;
				
			case(1101):
				sbDown = gp.maxWorldRow - 1;
				sbUp = 0;
				sbLeft = 0;
				sbRight = gp.maxScreenCol;
				break;
				
			case(1110):
				sbDown = (gp.maxScreenRow - 1) - 1;
				sbUp = 0;
				sbRight = gp.maxWorldCol;
				sbLeft = gp.maxWorldCol - gp.maxScreenCol;
				break;
				
			case(1111):
				sbDown = gp.maxWorldRow - 1;
				sbUp = 0;
				sbLeft = 0;
				sbRight = gp.maxWorldCol;
				break;
				
		}
	}
}
