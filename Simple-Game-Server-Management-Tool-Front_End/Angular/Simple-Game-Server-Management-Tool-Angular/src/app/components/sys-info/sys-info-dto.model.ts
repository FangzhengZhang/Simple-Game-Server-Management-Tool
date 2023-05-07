export interface SysInfoDtoModel {
  id?: number;
  osName: string;
  osVersion: string;
  osArch: string;
  javaVersion: string;
  pythonVersion: string;
  cpuModel: string;
  cpuCoreCount: number;
  cpuFrequency: number;
  memorySize:string;
  diskSize: string;
  diskType: string;
  diskMountPoint: string;
  diskFileSystem: string;
}
