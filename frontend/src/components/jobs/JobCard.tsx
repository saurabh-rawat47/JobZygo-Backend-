import { JobPost } from '@/types';
import { MapPin, Building, Clock, DollarSign, Briefcase } from 'lucide-react';

interface JobCardProps {
  job: JobPost;
  onApply?: (jobId: string) => void;
}

export default function JobCard({ job, onApply }: JobCardProps) {
  const formatSalary = (salary: number) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    }).format(salary);
  };

  const formatExperience = (exp: number) => {
    if (exp === 0) return 'Entry Level';
    if (exp === 1) return '1 year';
    return `${exp} years`;
  };

  return (
    <div className="bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow duration-200 border border-gray-200">
      <div className="p-6">
        <div className="flex justify-between items-start mb-4">
          <div>
            <h3 className="text-xl font-semibold text-gray-900 mb-2">
              {job.profile}
            </h3>
            <div className="flex items-center text-gray-600 mb-2">
              <Building className="w-4 h-4 mr-2" />
              <span className="text-sm">{job.companyName}</span>
            </div>
            <div className="flex items-center text-gray-600">
              <MapPin className="w-4 h-4 mr-2" />
              <span className="text-sm">{job.location}</span>
            </div>
          </div>
          <div className="text-right">
            <div className="text-lg font-semibold text-green-600">
              {formatSalary(job.salary)}
            </div>
            <div className="text-sm text-gray-500">per year</div>
          </div>
        </div>

        <div className="flex flex-wrap gap-2 mb-4">
          <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
            <Clock className="w-3 h-3 mr-1" />
            {job.jobType}
          </span>
          <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-purple-100 text-purple-800">
            <Briefcase className="w-3 h-3 mr-1" />
            {formatExperience(job.exp)}
          </span>
        </div>

        <p className="text-gray-600 text-sm mb-4 line-clamp-3">
          {job.desc}
        </p>

        <div className="mb-4">
          <h4 className="text-sm font-medium text-gray-900 mb-2">Technologies:</h4>
          <div className="flex flex-wrap gap-1">
            {job.techs.map((tech, index) => (
              <span
                key={`${job.id}-tech-${tech}-${index}`}
                className="inline-flex items-center px-2 py-1 rounded-md text-xs font-medium bg-gray-100 text-gray-800"
              >
                {tech}
              </span>
            ))}
          </div>
        </div>

        {onApply && (
          <button
            onClick={() => onApply(job.id!)}
            className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-colors duration-200 font-medium"
          >
            Apply Now
          </button>
        )}
      </div>
    </div>
  );
}


