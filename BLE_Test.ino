#include <SoftwareSerial.h>

SoftwareSerial mySerial(2, 3); // RX, TX

void setup() 
{
    mySerial.begin(9600);

    mySerial.write("CENT=OFF");
    mySerial.write("\r");
 
    mySerial.write("ADV ON");
    mySerial.write("\r");
}

void loop() 
{
}
