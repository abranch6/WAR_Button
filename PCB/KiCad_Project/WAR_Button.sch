EESchema Schematic File Version 2
LIBS:WAR_Button-rescue
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:bc118
LIBS:attiny
LIBS:war_power
LIBS:WAR_Button-cache
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L BC118 BLE1
U 1 1 560D65FC
P 9850 1950
F 0 "BLE1" H 9600 3050 60  0000 C CNN
F 1 "BC118" H 9600 3150 60  0000 C CNN
F 2 "bc118:bc118" H 9600 3150 60  0001 C CNN
F 3 "" H 9600 3150 60  0000 C CNN
	1    9850 1950
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR01
U 1 1 560D6740
P 8300 800
F 0 "#PWR01" H 8300 550 50  0001 C CNN
F 1 "GND" H 8300 650 50  0000 C CNN
F 2 "" H 8300 800 60  0000 C CNN
F 3 "" H 8300 800 60  0000 C CNN
	1    8300 800 
	0    1    1    0   
$EndComp
$Comp
L GND #PWR02
U 1 1 560D758B
P 800 2300
F 0 "#PWR02" H 800 2050 50  0001 C CNN
F 1 "GND" H 800 2150 50  0000 C CNN
F 2 "" H 800 2300 60  0000 C CNN
F 3 "" H 800 2300 60  0000 C CNN
	1    800  2300
	0    1    1    0   
$EndComp
$Comp
L C_Small C3
U 1 1 560D7779
P 8500 2300
F 0 "C3" H 8510 2370 50  0000 L CNN
F 1 "10nF" H 8510 2220 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 8500 2300 60  0001 C CNN
F 3 "" H 8500 2300 60  0000 C CNN
	1    8500 2300
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR03
U 1 1 560D7FCC
P 4350 2000
F 0 "#PWR03" H 4350 1750 50  0001 C CNN
F 1 "GND" H 4350 1850 50  0000 C CNN
F 2 "" H 4350 2000 60  0000 C CNN
F 3 "" H 4350 2000 60  0000 C CNN
	1    4350 2000
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW1
U 1 1 560D7FE8
P 3900 2000
F 0 "SW1" H 4050 2110 50  0000 C CNN
F 1 "SW_PUSH" H 3900 1920 50  0000 C CNN
F 2 "Buttons_Switches_SMD:SW_SPST_EVQP2" H 3900 2000 60  0001 C CNN
F 3 "" H 3900 2000 60  0000 C CNN
	1    3900 2000
	1    0    0    -1  
$EndComp
Text GLabel 10700 1100 2    60   Input ~ 0
BLE_TX
Text GLabel 10700 1200 2    60   Input ~ 0
BLE_RX
Text GLabel 4100 1200 2    60   Input ~ 0
BLE_RX
Text GLabel 4100 1350 2    60   Input ~ 0
BLE_TX
Text GLabel 3550 1400 2    60   Input ~ 0
WAKE
Text GLabel 10700 2600 2    60   Input ~ 0
WAKE
Text GLabel 3450 4450 0    60   Input ~ 0
P1
Text GLabel 3450 4350 0    60   Input ~ 0
P2
Text GLabel 3950 4450 2    60   Input ~ 0
P3
Text GLabel 3450 4550 0    60   Input ~ 0
P4
Text GLabel 3950 4350 2    60   Input ~ 0
PROG_VCC
Text GLabel 4200 4550 2    60   Input ~ 0
PROG_GND
Text GLabel 3150 1500 2    60   Input ~ 0
P1
Text GLabel 3150 1600 2    60   Input ~ 0
P2
Text GLabel 3150 1700 2    60   Input ~ 0
P3
Text GLabel 3150 2300 2    60   Input ~ 0
P4
$Comp
L LED D2
U 1 1 561F3B46
P 4100 1650
F 0 "D2" H 4100 1750 50  0000 C CNN
F 1 "LED" H 4100 1550 50  0000 C CNN
F 2 "LEDs:LED-0603" H 4100 1650 60  0001 C CNN
F 3 "" H 4100 1650 60  0000 C CNN
	1    4100 1650
	-1   0    0    1   
$EndComp
$Comp
L R_Small R3
U 1 1 561F3B97
P 3650 1650
F 0 "R3" H 3680 1670 50  0000 L CNN
F 1 "LED_R" H 3680 1610 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 3650 1650 60  0001 C CNN
F 3 "" H 3650 1650 60  0000 C CNN
	1    3650 1650
	0    1    1    0   
$EndComp
$Comp
L GND #PWR04
U 1 1 561F3C70
P 4400 1650
F 0 "#PWR04" H 4400 1400 50  0001 C CNN
F 1 "GND" H 4400 1500 50  0000 C CNN
F 2 "" H 4400 1650 60  0000 C CNN
F 3 "" H 4400 1650 60  0000 C CNN
	1    4400 1650
	0    -1   -1   0   
$EndComp
$Comp
L R_Small R4
U 1 1 56303048
P 10600 2800
F 0 "R4" H 10630 2820 50  0000 L CNN
F 1 "100K" H 10630 2760 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 10600 2800 60  0001 C CNN
F 3 "" H 10600 2800 60  0000 C CNN
	1    10600 2800
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR05
U 1 1 563030D2
P 10600 3000
F 0 "#PWR05" H 10600 2750 50  0001 C CNN
F 1 "GND" H 10600 2850 50  0000 C CNN
F 2 "" H 10600 3000 60  0000 C CNN
F 3 "" H 10600 3000 60  0000 C CNN
	1    10600 3000
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR06
U 1 1 56304562
P 5200 2100
F 0 "#PWR06" H 5200 1850 50  0001 C CNN
F 1 "GND" H 5200 1950 50  0000 C CNN
F 2 "" H 5200 2100 60  0000 C CNN
F 3 "" H 5200 2100 60  0000 C CNN
	1    5200 2100
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW2
U 1 1 56304568
P 4750 2100
F 0 "SW2" H 4900 2210 50  0000 C CNN
F 1 "SW_PUSH" H 4750 2020 50  0000 C CNN
F 2 "Buttons_Switches_SMD:SW_SPST_EVQP2" H 4750 2100 60  0001 C CNN
F 3 "" H 4750 2100 60  0000 C CNN
	1    4750 2100
	1    0    0    -1  
$EndComp
$Comp
L ATTINY84A-M-RESCUE-WAR_Button IC1
U 1 1 56304DEF
P 2100 1700
F 0 "IC1" H 1250 2450 40  0000 C CNN
F 1 "ATTINY84A-M" H 2800 950 40  0000 C CNN
F 2 "war_ic:qfn20" H 2100 1500 35  0000 C CIN
F 3 "" H 2100 1700 60  0000 C CNN
	1    2100 1700
	1    0    0    -1  
$EndComp
$Comp
L CONN_02X03 P3
U 1 1 56305C78
P 3700 4450
F 0 "P3" H 3700 4650 50  0000 C CNN
F 1 "HEADER" H 3700 4250 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_2x03" H 3700 3250 60  0001 C CNN
F 3 "" H 3700 3250 60  0000 C CNN
	1    3700 4450
	1    0    0    -1  
$EndComp
$Comp
L USB_OTG P1
U 1 1 563E8CC9
P 1400 4050
F 0 "P1" H 1725 3925 50  0000 C CNN
F 1 "USB_OTG" H 1400 4250 50  0000 C CNN
F 2 "war_power:USB_Micro-B" V 1350 3950 60  0001 C CNN
F 3 "" V 1350 3950 60  0000 C CNN
	1    1400 4050
	1    0    0    -1  
$EndComp
$Comp
L C_Small C2
U 1 1 563E8DC4
P 2850 6250
F 0 "C2" H 2860 6320 50  0000 L CNN
F 1 "4.7uF" H 2860 6170 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 2850 6250 60  0001 C CNN
F 3 "" H 2850 6250 60  0000 C CNN
	1    2850 6250
	-1   0    0    1   
$EndComp
$Comp
L C_Small C1
U 1 1 563E8E09
P 1000 4600
F 0 "C1" H 1010 4670 50  0000 L CNN
F 1 "4.7uF" H 1010 4520 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 1000 4600 60  0001 C CNN
F 3 "" H 1000 4600 60  0000 C CNN
	1    1000 4600
	0    1    1    0   
$EndComp
$Comp
L LED D1
U 1 1 563E8E5E
P 2700 5750
F 0 "D1" H 2700 5850 50  0000 C CNN
F 1 "LED" H 2700 5650 50  0000 C CNN
F 2 "LEDs:LED-0603" H 2700 5750 60  0001 C CNN
F 3 "" H 2700 5750 60  0000 C CNN
	1    2700 5750
	-1   0    0    1   
$EndComp
$Comp
L R_Small R1
U 1 1 563E8ED3
P 2400 5750
F 0 "R1" H 2430 5770 50  0000 L CNN
F 1 "LED_R" H 2430 5710 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 2400 5750 60  0001 C CNN
F 3 "" H 2400 5750 60  0000 C CNN
	1    2400 5750
	0    -1   -1   0   
$EndComp
$Comp
L MCP73831 Q1
U 1 1 563E8F3E
P 3400 5900
F 0 "Q1" H 3750 6000 40  0000 R CNN
F 1 "MCP73831" H 4000 5900 40  0000 R TNN
F 2 "Housings_SOT-23_SOT-143_TSOT-6:SOT-23-5" H 3800 5800 30  0000 C CNN
F 3 "" H 3450 5900 60  0000 C CNN
	1    3400 5900
	0    1    1    0   
$EndComp
$Comp
L GND #PWR07
U 1 1 563E91F9
P 1600 4350
F 0 "#PWR07" H 1600 4100 50  0001 C CNN
F 1 "GND" H 1600 4200 50  0000 C CNN
F 2 "" H 1600 4350 60  0000 C CNN
F 3 "" H 1600 4350 60  0000 C CNN
	1    1600 4350
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR08
U 1 1 563E9261
P 2950 5900
F 0 "#PWR08" H 2950 5650 50  0001 C CNN
F 1 "GND" H 2950 5750 50  0000 C CNN
F 2 "" H 2950 5900 60  0000 C CNN
F 3 "" H 2950 5900 60  0000 C CNN
	1    2950 5900
	0    1    1    0   
$EndComp
Text GLabel 1200 4700 3    60   Input ~ 0
USB_VCC
Text GLabel 3850 6050 2    60   Input ~ 0
USB_VCC
$Comp
L GND #PWR09
U 1 1 563E96BF
P 900 4600
F 0 "#PWR09" H 900 4350 50  0001 C CNN
F 1 "GND" H 900 4450 50  0000 C CNN
F 2 "" H 900 4600 60  0000 C CNN
F 3 "" H 900 4600 60  0000 C CNN
	1    900  4600
	0    1    1    0   
$EndComp
$Comp
L GND #PWR010
U 1 1 563E9861
P 2850 6350
F 0 "#PWR010" H 2850 6100 50  0001 C CNN
F 1 "GND" H 2850 6200 50  0000 C CNN
F 2 "" H 2850 6350 60  0000 C CNN
F 3 "" H 2850 6350 60  0000 C CNN
	1    2850 6350
	1    0    0    -1  
$EndComp
$Comp
L R_Small R2
U 1 1 563E9ABE
P 3850 5500
F 0 "R2" H 3880 5520 50  0000 L CNN
F 1 "PROG" H 3880 5460 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 3850 5500 60  0001 C CNN
F 3 "" H 3850 5500 60  0000 C CNN
	1    3850 5500
	-1   0    0    1   
$EndComp
Wire Wire Line
	8300 800  8800 800 
Wire Wire Line
	8800 800  8800 2100
Connection ~ 8800 900 
Connection ~ 8800 1000
Connection ~ 8800 1100
Connection ~ 8800 1200
Connection ~ 8800 1300
Connection ~ 8800 1400
Connection ~ 8800 1500
Connection ~ 8800 1600
Connection ~ 8800 1700
Connection ~ 8800 1800
Connection ~ 8800 1900
Connection ~ 8800 2000
Wire Wire Line
	800  1100 1050 1100
Wire Wire Line
	800  2300 1050 2300
Wire Wire Line
	8250 2600 8800 2600
Wire Wire Line
	8800 2600 8800 2500
Wire Wire Line
	8500 2600 8500 2400
Connection ~ 8500 2600
Wire Wire Line
	8500 2200 8500 2100
Wire Wire Line
	8500 2100 8800 2100
Wire Wire Line
	4200 2000 4350 2000
Wire Wire Line
	3600 2000 3150 2000
Wire Wire Line
	10500 1100 10700 1100
Wire Wire Line
	10500 1200 10700 1200
Wire Wire Line
	3150 1200 4100 1200
Wire Wire Line
	3150 1300 4100 1300
Wire Wire Line
	4100 1300 4100 1350
Wire Wire Line
	3550 1400 3150 1400
Wire Wire Line
	3150 1800 3550 1800
Wire Wire Line
	3550 1800 3550 1650
Wire Wire Line
	3750 1650 3900 1650
Wire Wire Line
	4300 1650 4400 1650
Wire Wire Line
	10500 2600 10700 2600
Wire Wire Line
	10600 2700 10600 2600
Connection ~ 10600 2600
Wire Wire Line
	10600 2900 10600 3000
Wire Wire Line
	5050 2100 5200 2100
Wire Wire Line
	4450 2100 4150 2100
Wire Wire Line
	4150 2100 4150 2150
Wire Wire Line
	4150 2150 3700 2150
Wire Wire Line
	3700 2150 3700 2100
Wire Wire Line
	3700 2100 3150 2100
Wire Wire Line
	1000 2200 900  2200
Wire Wire Line
	900  2200 900  2300
Connection ~ 900  2300
Wire Wire Line
	2900 5750 2950 5750
Wire Wire Line
	2200 6050 2950 6050
Wire Wire Line
	2850 6050 2850 6150
Connection ~ 1200 4600
Wire Wire Line
	3850 5750 3850 5600
$Comp
L GND #PWR011
U 1 1 563E9C53
P 3850 5400
F 0 "#PWR011" H 3850 5150 50  0001 C CNN
F 1 "GND" H 3850 5250 50  0000 C CNN
F 2 "" H 3850 5400 60  0000 C CNN
F 3 "" H 3850 5400 60  0000 C CNN
	1    3850 5400
	-1   0    0    1   
$EndComp
$Comp
L CONN_01X02 P2
U 1 1 563E9ED0
P 2450 4800
F 0 "P2" H 2450 4950 50  0000 C CNN
F 1 "BATT" V 2550 4800 50  0000 C CNN
F 2 "war_power:JST_2" H 2450 4800 60  0001 C CNN
F 3 "" H 2450 4800 60  0000 C CNN
	1    2450 4800
	0    1    1    0   
$EndComp
$Comp
L GND #PWR012
U 1 1 563E9F4A
P 2600 4550
F 0 "#PWR012" H 2600 4300 50  0001 C CNN
F 1 "GND" H 2600 4400 50  0000 C CNN
F 2 "" H 2600 4550 60  0000 C CNN
F 3 "" H 2600 4550 60  0000 C CNN
	1    2600 4550
	-1   0    0    1   
$EndComp
Text GLabel 2400 4600 1    60   Input ~ 0
BATT_VCC
Wire Wire Line
	2500 4600 2600 4600
Wire Wire Line
	2600 4600 2600 4550
Text GLabel 2550 6050 3    60   Input ~ 0
BATT_VCC
Connection ~ 2850 6050
Text GLabel 2300 5750 0    60   Input ~ 0
USB_VCC
Wire Wire Line
	1200 4350 1200 4700
Wire Wire Line
	3950 4550 4200 4550
$Comp
L GND #PWR013
U 1 1 563EDE96
P 4100 4800
F 0 "#PWR013" H 4100 4550 50  0001 C CNN
F 1 "GND" H 4100 4650 50  0000 C CNN
F 2 "" H 4100 4800 60  0000 C CNN
F 3 "" H 4100 4800 60  0000 C CNN
	1    4100 4800
	1    0    0    -1  
$EndComp
Wire Wire Line
	4100 4550 4100 4800
Connection ~ 4100 4550
$Comp
L CONN_01X01 P4
U 1 1 563F032D
P 6800 900
F 0 "P4" H 6800 1000 50  0000 C CNN
F 1 "RX" V 6900 900 50  0000 C CNN
F 2 "Measurement_Points:Measurement_Point_Square-SMD-Pad_Small" H 6800 900 60  0001 C CNN
F 3 "" H 6800 900 60  0000 C CNN
	1    6800 900 
	1    0    0    -1  
$EndComp
Text GLabel 6600 900  0    60   Input ~ 0
BLE_RX
$Comp
L CONN_01X01 P5
U 1 1 563F0448
P 6800 1150
F 0 "P5" H 6800 1250 50  0000 C CNN
F 1 "TX" V 6900 1150 50  0000 C CNN
F 2 "Measurement_Points:Measurement_Point_Square-SMD-Pad_Small" H 6800 1150 60  0001 C CNN
F 3 "" H 6800 1150 60  0000 C CNN
	1    6800 1150
	1    0    0    -1  
$EndComp
Text GLabel 6600 1150 0    60   Input ~ 0
BLE_TX
$Comp
L CONN_01X01 P6
U 1 1 563F087A
P 6800 1400
F 0 "P6" H 6800 1500 50  0000 C CNN
F 1 "GND" V 6900 1400 50  0000 C CNN
F 2 "Measurement_Points:Measurement_Point_Square-SMD-Pad_Small" H 6800 1400 60  0001 C CNN
F 3 "" H 6800 1400 60  0000 C CNN
	1    6800 1400
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X01 P7
U 1 1 563F08ED
P 6800 1650
F 0 "P7" H 6800 1750 50  0000 C CNN
F 1 "BLED1" V 6900 1650 50  0000 C CNN
F 2 "Measurement_Points:Measurement_Point_Square-SMD-Pad_Small" H 6800 1650 60  0001 C CNN
F 3 "" H 6800 1650 60  0000 C CNN
	1    6800 1650
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X01 P8
U 1 1 563F094E
P 6800 1950
F 0 "P8" H 6800 2050 50  0000 C CNN
F 1 "BLED2" V 6900 1950 50  0000 C CNN
F 2 "Measurement_Points:Measurement_Point_Square-SMD-Pad_Small" H 6800 1950 60  0001 C CNN
F 3 "" H 6800 1950 60  0000 C CNN
	1    6800 1950
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR014
U 1 1 563F0CC0
P 6600 1400
F 0 "#PWR014" H 6600 1150 50  0001 C CNN
F 1 "GND" H 6600 1250 50  0000 C CNN
F 2 "" H 6600 1400 60  0000 C CNN
F 3 "" H 6600 1400 60  0000 C CNN
	1    6600 1400
	0    1    1    0   
$EndComp
Text GLabel 10500 1400 2    60   Input ~ 0
BLED1
Text GLabel 10500 2300 2    60   Input ~ 0
BLED2
Text GLabel 6600 1650 0    60   Input ~ 0
BLED1
Text GLabel 6600 1950 0    60   Input ~ 0
BLED2
Text GLabel 950  6150 3    60   Input ~ 0
LOAD_VCC
Text GLabel 800  1200 3    60   Input ~ 0
LOAD_VCC
Wire Wire Line
	800  1100 800  1200
Text GLabel 3500 1100 1    60   Input ~ 0
LOAD_VCC
Wire Wire Line
	3500 1100 3150 1100
Text GLabel 8250 2600 1    60   Input ~ 0
LOAD_VCC
Text GLabel 2200 6400 3    60   Input ~ 0
USB_VCC
Wire Wire Line
	950  6150 1300 6150
$Comp
L NTR2101PT1G Q2
U 1 1 5643C51D
P 1800 6150
F 0 "Q2" H 1850 6350 60  0000 C CNN
F 1 "NTR2101PT1G" H 1850 5950 60  0000 C CNN
F 2 "Housings_SOT-23_SOT-143_TSOT-6:SOT-23" H 1850 6350 60  0001 C CNN
F 3 "" H 1850 6350 60  0000 C CNN
	1    1800 6150
	-1   0    0    1   
$EndComp
Wire Wire Line
	2200 6400 2200 6250
$Comp
L C_Small C4
U 1 1 5644026D
P 1300 6350
F 0 "C4" H 1310 6420 50  0000 L CNN
F 1 "1uF" H 1310 6270 50  0000 L CNN
F 2 "Capacitors_SMD:C_0603" H 1300 6350 60  0001 C CNN
F 3 "" H 1300 6350 60  0000 C CNN
	1    1300 6350
	-1   0    0    1   
$EndComp
Wire Wire Line
	1300 6150 1300 6250
$Comp
L GND #PWR015
U 1 1 56440362
P 1300 6450
F 0 "#PWR015" H 1300 6200 50  0001 C CNN
F 1 "GND" H 1300 6300 50  0000 C CNN
F 2 "" H 1300 6450 60  0000 C CNN
F 3 "" H 1300 6450 60  0000 C CNN
	1    1300 6450
	1    0    0    -1  
$EndComp
Text GLabel 1150 6150 1    60   Input ~ 0
PROG_VCC
Wire Wire Line
	1100 4600 1200 4600
$Comp
L R_Small R5
U 1 1 5644486A
P 2050 6600
F 0 "R5" H 2080 6620 50  0000 L CNN
F 1 "100K" H 2080 6560 50  0000 L CNN
F 2 "Resistors_SMD:R_0603" H 2050 6600 60  0001 C CNN
F 3 "" H 2050 6600 60  0000 C CNN
	1    2050 6600
	-1   0    0    1   
$EndComp
$Comp
L GND #PWR016
U 1 1 56444F96
P 2050 6700
F 0 "#PWR016" H 2050 6450 50  0001 C CNN
F 1 "GND" H 2050 6550 50  0000 C CNN
F 2 "" H 2050 6700 60  0000 C CNN
F 3 "" H 2050 6700 60  0000 C CNN
	1    2050 6700
	1    0    0    -1  
$EndComp
Wire Wire Line
	2050 6500 2050 6350
Wire Wire Line
	2050 6350 2200 6350
Connection ~ 2200 6350
$EndSCHEMATC
