void setup() {
  // initialize both serial ports:
  Serial.begin(9600);
  Serial1.begin(9600);
  Serial.println("Hello World!!");
}

void loop() {
  // read from port 1, send to port 0:
  if (Serial1.available()) {
    int inByte = Serial1.read();
    Serial.write(inByte);
  }
}
