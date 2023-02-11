package org.teamb58.whatsappmini;

import android.content.pm.Signature;
import android.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SignatureSpoof {

    public static byte[] Hash() {
        return Base64.decode("AY7vaXkOu58IjcfkEXWJJA==", 0);
    }

    public static SecretKey SecretKey() {
        byte[] bArr = {121, 5, 121, 106, -81, -62, -125, -83, -58, -78, -83, 108, -78, 19, 125, 79, 120, 33, -7, 69, 41, -45, 2, 48, -45, 24, 7, 97, 61, 91, -100, 40, -60, -88, -30, 80, 40, 36, 107, 91, 23, 64, 123, 108, -81, -80, 55, -126, 36, -65, -104, -32, 109, -49, 68, 61, -121, 80, 94, -24, 82, 8, -122, -93};
        return new SecretKeySpec(bArr, 0, bArr.length, "PBKDF2WithHmacSHA1And8BIT");
    }

    public static byte[] Signature() {
        return new byte[]{48, -126, 3, 50, 48, -126, 2, -16, -96, 3, 2, 1, 2, 2, 4, 76, 37, 54, -92, 48, 11, 6, 7, 42, -122, 72, -50, 56, 4, 3, 5, 0, 48, 124, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 19, 48, 17, 6, 3, 85, 4, 8, 19, 10, 67, 97, 108, 105, 102, 111, 114, 110, 105, 97, 49, 20, 48, 18, 6, 3, 85, 4, 7, 19, 11, 83, 97, 110, 116, 97, 32, 67, 108, 97, 114, 97, 49, 22, 48, 20, 6, 3, 85, 4, 10, 19, 13, 87, 104, 97, 116, 115, 65, 112, 112, 32, 73, 110, 99, 46, 49, 20, 48, 18, 6, 3, 85, 4, 11, 19, 11, 69, 110, 103, 105, 110, 101, 101, 114, 105, 110, 103, 49, 20, 48, 18, 6, 3, 85, 4, 3, 19, 11, 66, 114, 105, 97, 110, 32, 65, 99, 116, 111, 110, 48, 30, 23, 13, 49, 48, 48, 54, 50, 53, 50, 51, 48, 55, 49, 54, 90, 23, 13, 52, 52, 48, 50, 49, 53, 50, 51, 48, 55, 49, 54, 90, 48, 124, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 19, 48, 17, 6, 3, 85, 4, 8, 19, 10, 67, 97, 108, 105, 102, 111, 114, 110, 105, 97, 49, 20, 48, 18, 6, 3, 85, 4, 7, 19, 11, 83, 97, 110, 116, 97, 32, 67, 108, 97, 114, 97, 49, 22, 48, 20, 6, 3, 85, 4, 10, 19, 13, 87, 104, 97, 116, 115, 65, 112, 112, 32, 73, 110, 99, 46, 49, 20, 48, 18, 6, 3, 85, 4, 11, 19, 11, 69, 110, 103, 105, 110, 101, 101, 114, 105, 110, 103, 49, 20, 48, 18, 6, 3, 85, 4, 3, 19, 11, 66, 114, 105, 97, 110, 32, 65, 99, 116, 111, 110, 48, -126, 1, -72, 48, -126, 1, 44, 6, 7, 42, -122, 72, -50, 56, 4, 1, 48, -126, 1, 31, 2, -127, -127, 0, -3, Byte.MAX_VALUE, 83, -127, 29, 117, 18, 41, 82, -33, 74, -100, 46, -20, -28, -25, -10, 17, -73, 82, 60, -17, 68, 0, -61, 30, 63, Byte.MIN_VALUE, -74, 81, 38, 105, 69, 93, 64, 34, 81, -5, 89, 61, -115, 88, -6, -65, -59, -11, -70, 48, -10, -53, -101, 85, 108, -41, -127, 59, Byte.MIN_VALUE, 29, 52, 111, -14, 102, 96, -73, 107, -103, 80, -91, -92, -97, -97, -24, 4, 123, 16, 34, -62, 79, -69, -87, -41, -2, -73, -58, 27, -8, 59, 87, -25, -58, -88, -90, 21, 15, 4, -5, -125, -10, -45, -59, 30, -61, 2, 53, 84, 19, 90, 22, -111, 50, -10, 117, -13, -82, 43, 97, -41, 42, -17, -14, 34, 3, 25, -99, -47, 72, 1, -57, 2, 21, 0, -105, 96, 80, -113, 21, 35, 11, -52, -78, -110, -71, -126, -94, -21, -124, 11, -16, 88, 28, -11, 2, -127, -127, 0, -9, -31, -96, -123, -42, -101, 61, -34, -53, -68, -85, 92, 54, -72, 87, -71, 121, -108, -81, -69, -6, 58, -22, -126, -7, 87, 76, 11, 61, 7, -126, 103, 81, 89, 87, -114, -70, -44, 89, 79, -26, 113, 7, 16, -127, Byte.MIN_VALUE, -76, 73, 22, 113, 35, -24, 76, 40, 22, 19, -73, -49, 9, 50, -116, -56, -90, -31, 60, 22, 122, -117, 84, 124, -115, 40, -32, -93, -82, 30, 43, -77, -90, 117, -111, 110, -93, Byte.MAX_VALUE, 11, -6, 33, 53, 98, -15, -5, 98, 122, 1, 36, 59, -52, -92, -15, -66, -88, 81, -112, -119, -88, -125, -33, -31, 90, -27, -97, 6, -110, -117, 102, 94, Byte.MIN_VALUE, 123, 85, 37, 100, 1, 76, 59, -2, -49, 73, 42, 3, -127, -123, 0, 2, -127, -127, 0, -47, 25, -117, 75, -127, 104, 123, -49, 36, 109, 65, -88, -89, 37, -16, -87, -119, -91, 27, -50, 50, 110, -124, -56, 40, -31, -11, 86, 100, -117, -41, 29, -92, -121, 5, 77, 109, -25, 15, -1, 75, 73, 67, 43, 104, 98, -86, 72, -4, 42, -109, 22, 27, 44, 21, -94, -1, 94, 103, 22, 114, -33, -75, 118, -23, -47, 42, -81, -9, 54, -101, -102, -103, -48, 79, -78, -99, 43, -69, -78, -91, 3, -18, 65, -79, -1, 55, -120, 112, 100, -12, 31, -30, Byte.MIN_VALUE, 86, 9, 6, 53, 0, -88, -27, 71, 52, -110, -126, -47, 89, -127, -51, -75, -118, 8, -66, -34, 81, -35, 126, -104, 103, 41, 91, 61, -5, 69, -1, -58, -78, 89, 48, 11, 6, 7, 42, -122, 72, -50, 56, 4, 3, 5, 0, 3, 47, 0, 48, 44, 2, 20, 0, -90, 2, -89, 71, 122, -49, -124, 16, 119, 35, 123, -32, -112, -33, 67, 101, -126, -54, 47, 2, 20, 53, 12, -32, 38, -115, 7, -25, 30, 85, 119, 74, -76, -22, -51, 77, 7, 28, -47, -17, -83};
    }

    public static Signature[] Signatures() {
        return new Signature[]{new Signature(Signature())};
    }
}
