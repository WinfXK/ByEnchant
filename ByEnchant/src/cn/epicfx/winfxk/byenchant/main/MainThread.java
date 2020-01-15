package cn.epicfx.winfxk.byenchant.main;

/**
 * @author Winfxk
 */
public class MainThread extends Thread {
	private Main main;
	private int Key;

	public MainThread(Main main, int i) {
		this.main = main;
		Key = i;
	}

	@Override
	public void run() {
		switch (Key) {
		case 0:
			try {
				while (true) {
					main.handler.a = main.handler.x;
					for (int i = 0; i < 2; i++) {
						sleep(100);
						while (main.handler.a < main.handler.y) {
							main.handler.a++;
							main.handler.sendEmptyMessage(0);
							sleep(1);
						}
						sleep(100);
						while (main.handler.a > main.handler.x) {
							main.handler.a--;
							main.handler.sendEmptyMessage(0);
							sleep(1);
						}
						sleep(100);
					}
					sleep(500);
					for (int i = 0; i < 3; i++) {
						while (main.handler.a < main.handler.z) {
							main.handler.a++;
							main.handler.sendEmptyMessage(0);
							sleep(1);
						}
						sleep(100);
						while (main.handler.a > main.handler.x) {
							main.handler.a--;
							main.handler.sendEmptyMessage(0);
							sleep(1);
						}
						sleep(100);
					}
					sleep(2000);
				}
			} catch (Exception e) {
			}
			break;
		case 1:
			try {
				while (main.isOK) {
					main.handler.e = main.handler.q;
					while (main.handler.e < main.handler.r) {
						main.handler.e++;
						main.handler.sendEmptyMessage(1);
						if (!main.isOK)
							break;
						sleep(2);
					}
					if (!main.isOK)
						break;
					while (main.handler.e > main.handler.w) {
						main.handler.e--;
						main.handler.sendEmptyMessage(1);
						if (!main.isOK)
							break;
						sleep(2);
					}
					if (!main.isOK)
						break;
					while (main.handler.e < main.handler.q) {
						main.handler.e++;
						main.handler.sendEmptyMessage(1);
						if (!main.isOK)
							break;
						sleep(2);
					}
					if (!main.isOK)
						break;
				}
			} catch (Exception e) {
			}
			break;
		case 2:
			while (true) {
				if (main.data.size() < 1) {
					if (!main.isOK) {
						main.isOK = true;
						new MainThread(main, 1).start();
					}
				} else if (main.isOK) {
					main.handler.sendEmptyMessage(2);
					main.isOK = false;
				}
			}
		}
	}
}
