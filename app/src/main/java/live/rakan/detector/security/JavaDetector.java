package live.rakan.detector.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaDetector { public boolean checkSuExists() {
    Process process = null;
    try {
        process = Runtime.getRuntime().exec(new String[]{"which", "su"});
        boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
        if (process != null) {
            process.destroy();
        }
        return z;
    } catch (Throwable unused) {
        if (process != null) {
            process.destroy();
        }
        return false;
    }
}
}
